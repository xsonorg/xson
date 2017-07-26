package org.xson.core.generator;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;

import org.xson.core.XsonConst;
import org.xson.core.XsonWriter;
import org.xson.core.asm.ClassWriter;
import org.xson.core.asm.MethodVisitor;
import org.xson.core.asm.Opcodes;
import org.xson.core.asm.Type;
import org.xson.core.serializer.ArraySerializer;
import org.xson.core.serializer.CollectionSerializer;
import org.xson.core.serializer.EnumSerializer;
import org.xson.core.serializer.MapSerializer;
import org.xson.core.serializer.TimeZoneSerializer;
import org.xson.core.util.FieldModel;
import org.xson.core.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonASMSerializerFactory implements Opcodes {

	private final static int		LOCAL_VARIABLE_START_INDEX	= 2;

	private final static int		OBJECT_TARGET_INDEX			= 1;

	private final static int		BYTEMODEL_INDEX				= 2;

	private final static String		OBJECT_CLASSNAME			= "java/lang/Object";			// Object对象名

	// private final static String EXTEND_INTERFACE_NAME = "org.xson.XsonWriter";
	// private final static String BYTEMODEL_DESC = "org/xson/ByteModel";
	// private final static String EXTEND_INTERFACE_NAME = "com.github.xsonorg.XsonWriter";
	// private final static String BYTEMODEL_DESC = "com/github/xsonorg/ByteModel";

	private final static String		EXTEND_INTERFACE_NAME		= "org.xson.core.XsonWriter";
	private final static String		BYTEMODEL_DESC				= "org/xson/core/WriterModel";

	private final static AtomicLong	seed						= new AtomicLong();
	private static Method			INTERFACE_METHOD			= null;

	static {
		try {
			INTERFACE_METHOD = XsonTypeUtils.findClass(EXTEND_INTERFACE_NAME).getDeclaredMethods()[0];
		} catch (Throwable e) {
		}
	}

	private static String getGenClassName(String baseBame) {
		return baseBame + "_" + seed.incrementAndGet();
	}

	private static XsonWriter generateInstance(Class<?> proxyClazz) throws XsonGeneratorException {
		try {
			String targetClassName = getGenClassName(XsonTypeUtils.getLittleClassName(EXTEND_INTERFACE_NAME));
			String objectClassName = OBJECT_CLASSNAME;
			String superClassName = objectClassName;
			String[] interfacesName = { XsonTypeUtils.toAsmClass(EXTEND_INTERFACE_NAME) };
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			String signature = null;
			classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, targetClassName, signature, superClassName, interfacesName);
			createConstructor(classWriter, superClassName);
			createMethod(classWriter, INTERFACE_METHOD, proxyClazz);
			Object instance = XsonTypeUtils.createByteCode(classWriter, targetClassName);
			return (XsonWriter) instance;
		} catch (Exception e) {
			throw new XsonGeneratorException(e);
		}
	}

	private static void createConstructor(ClassWriter classWriter, String superClassName) {
		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superClassName, "<init>", "()V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
	}

	private static void createMethod(ClassWriter classWriter, Method method, Class<?> proxyClazz) {
		String desc = XsonTypeUtils.getDesc(method);
		String[] exceptions = XsonTypeUtils.getExceptionDesc(method);
		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, method.getName(), desc, null, exceptions);
		createMethodBody(mv, proxyClazz, LOCAL_VARIABLE_START_INDEX + 1);
		mv.visitInsn(RETURN);
		mv.visitMaxs(3, 4);
		mv.visitEnd();
	}

	private static void createMethodBody(MethodVisitor mv, Class<?> proxyClazz, int lvIndex) {
		mv.visitVarInsn(ALOAD, BYTEMODEL_INDEX);
		mv.visitLdcInsn(Type.getType(XsonTypeUtils.getDesc(proxyClazz)));
		mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "appendCreateUserObject", "(Ljava/lang/Class;)V");

		if (LOCAL_VARIABLE_START_INDEX == (lvIndex - 1)) {
			mv.visitVarInsn(ALOAD, OBJECT_TARGET_INDEX);
			mv.visitTypeInsn(CHECKCAST, XsonTypeUtils.toAsmClass(proxyClazz.getName()));
			mv.visitVarInsn(ASTORE, lvIndex);
		}

		List<FieldModel> fieldModelList = XsonTypeUtils.getFieldModels(proxyClazz);
		for (FieldModel fieldModel : fieldModelList) {
			processfieldModel(fieldModel, mv, lvIndex);
		}
	}

	// private static void processfieldModel(FieldModel fieldModel, MethodVisitor mv, int lvIndex) {
	// if (fieldModel.isBaseType()) {
	// XsonGenerator generator = XsonSupport.getXsonGenerator(fieldModel.getFieldTypeConst());
	// mv.visitVarInsn(ALOAD, BYTEMODEL_INDEX);
	// mv.visitVarInsn(ALOAD, lvIndex);//
	// mv.visitMethodInsn(INVOKEVIRTUAL, fieldModel.getOwnerDesc(), fieldModel.getGetterName(), fieldModel.getGetterDesc());
	// generator.generateWriterCode(mv);
	// mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "append", "([B)V");
	// } else {
	// mv.visitVarInsn(ALOAD, BYTEMODEL_INDEX);
	// mv.visitVarInsn(ALOAD, lvIndex);//
	// mv.visitMethodInsn(INVOKEVIRTUAL, fieldModel.getOwnerDesc(), fieldModel.getGetterName(), fieldModel.getGetterDesc());
	// mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeObject", "(Ljava/lang/Object;)V");
	// }
	// }

	private static void processfieldModel(FieldModel fieldModel, MethodVisitor mv, int lvIndex) {
		if (fieldModel.isBaseType()) {
			mv.visitVarInsn(ALOAD, BYTEMODEL_INDEX);
			mv.visitVarInsn(ALOAD, lvIndex);//
			mv.visitMethodInsn(INVOKEVIRTUAL, fieldModel.getOwnerDesc(), fieldModel.getGetterName(), fieldModel.getGetterDesc());
			// mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "append", "([B)V");

			Class<?> fieldClass = fieldModel.getFieldClass();
			if (byte.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeByte", "(B)V");
			} else if (short.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeShort", "(S)V");
			} else if (int.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeInt", "(I)V");
			} else if (long.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeLong", "(J)V");
			} else if (float.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeFloat", "(F)V");
			} else if (double.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeDouble", "(D)V");
			} else if (boolean.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeBoolean", "(Z)V");
			} else if (char.class == fieldClass) {
				mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeChar", "(C)V");
			}
		} else {
			mv.visitVarInsn(ALOAD, BYTEMODEL_INDEX);
			mv.visitVarInsn(ALOAD, lvIndex);//
			mv.visitMethodInsn(INVOKEVIRTUAL, fieldModel.getOwnerDesc(), fieldModel.getGetterName(), fieldModel.getGetterDesc());
			mv.visitMethodInsn(INVOKEVIRTUAL, BYTEMODEL_DESC, "writeObject", "(Ljava/lang/Object;)V");
		}
	}

	public static XsonWriter getWriter(Object object) {
		Class<?> clazz = object.getClass();
		XsonWriter writer = XsonConst.WRITER_MAP.get(clazz);
		if (null != writer) {
			return writer;
		}
		if (clazz.isArray()) {
			return ArraySerializer.instance;
		}
		if (object instanceof Collection) {
			return CollectionSerializer.instance;
		}
		if (object instanceof Map) {
			return MapSerializer.instance;
		}
		if (object instanceof Enum) {
			return EnumSerializer.instance;
		}
		if (object instanceof TimeZone) {
			return TimeZoneSerializer.instance;
		}
		writer = generateInstance(clazz);
		XsonConst.WRITER_MAP.put(clazz, writer);
		return writer;
	}

}
