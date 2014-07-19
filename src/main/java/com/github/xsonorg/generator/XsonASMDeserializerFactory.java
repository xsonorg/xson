package com.github.xsonorg.generator;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonGenerator;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.XsonSupport;
import com.github.xsonorg.asm.ClassWriter;
import com.github.xsonorg.asm.MethodVisitor;
import com.github.xsonorg.asm.Opcodes;
import com.github.xsonorg.deserializer.XsonDeserializerException;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.FieldModel;
import com.github.xsonorg.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonASMDeserializerFactory implements Opcodes {

	private final static int LOCAL_VARIABLE_START_INDEX 	= 1;
	
	private final static int MODEL_INDEX 					= 1;
	
	private final static String OBJECT_CLASSNAME 			= "java/lang/Object";
	
//	private final static String EXTEND_INTERFACE_NAME 		= "org.xson.XsonReader";
//	
//	private final static String READERMODEL_DESC 			= "org/xson/ReaderModel";
	
	private final static String EXTEND_INTERFACE_NAME 		= "com.github.xsonorg.XsonReader";
	
	private final static String READERMODEL_DESC 			= "com/github/xsonorg/ReaderModel";
	
	private final static int OBJECT_ARRAY_KEY 				= ByteUtils.byteToInt(XsonConst.OBJECT)
			+ ByteUtils.byteToInt(XsonConst.CONTROL_CREATE_END);
	
	private final static AtomicLong seed 					= new AtomicLong();
	
	private static Method INTERFACE_METHOD 					= null;
	
	static{
		try {
			INTERFACE_METHOD = XsonTypeUtils.findClass(EXTEND_INTERFACE_NAME).getDeclaredMethods()[0];
		} catch (Throwable e) {}
	}
	
	private static String getGenClassName(String baseBame) {
		return baseBame + "_" + seed.incrementAndGet();
	}
	
	private static XsonReader generateInstance(Class<?> proxyClazz) throws XsonGeneratorException {
		try {
			String targetClassName = getGenClassName(XsonTypeUtils.getLittleClassName(EXTEND_INTERFACE_NAME));
			String objectClassName = OBJECT_CLASSNAME;
			String superClassName = objectClassName;
			String[] interfacesName = {XsonTypeUtils.toAsmClass(EXTEND_INTERFACE_NAME)};
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			String signature = null;
			classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
					targetClassName, signature, superClassName, interfacesName);
			createConstructor(classWriter, superClassName);
			createMethod(classWriter, INTERFACE_METHOD, proxyClazz);
			Object instance = XsonTypeUtils.createByteCode(classWriter, targetClassName);
			return (XsonReader) instance;
		} catch (Exception e) {
			throw new XsonGeneratorException(e);
		}
	}
	
	private static void createConstructor(ClassWriter classWriter, String superClassName) {
		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
				"<init>", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superClassName, "<init>",
				"()V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
	}
	
	private static void createMethod(ClassWriter classWriter, Method method, 
			Class<?> proxyClazz) {	
		String desc = XsonTypeUtils.getDesc(method);
		String[] exceptions = XsonTypeUtils.getExceptionDesc(method);
		
		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
				method.getName(), desc, null, exceptions);
		createMethodBody(mv, proxyClazz, LOCAL_VARIABLE_START_INDEX + 1);
		mv.visitMaxs(3, 4);
		mv.visitEnd();
	}
	
	private static void createMethodBody(MethodVisitor mv, Class<?> proxyClazz, int lvIndex) {
		String createClassName = XsonTypeUtils.toAsmClass(proxyClazz.getName());
		mv.visitTypeInsn(NEW, createClassName);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, createClassName, "<init>", "()V");
		mv.visitVarInsn(ASTORE, lvIndex);
		
		mv.visitVarInsn(ALOAD, MODEL_INDEX);
		mv.visitVarInsn(ALOAD, lvIndex);
		mv.visitMethodInsn(INVOKEVIRTUAL, READERMODEL_DESC, "appendObject", "(Ljava/lang/Object;)V");
		
		List<FieldModel> fieldModelList = XsonTypeUtils.getFieldModels(proxyClazz);
		for (FieldModel fieldModel : fieldModelList) {
			processfieldModel(fieldModel, mv, lvIndex);
		}
		
		mv.visitVarInsn(ALOAD, lvIndex);
		mv.visitInsn(ARETURN);
	}
	
	private static void processfieldModel(FieldModel fieldModel, MethodVisitor mv, int lvIndex){
		if(fieldModel.isBaseType()){
			XsonGenerator generator = XsonSupport.getXsonGenerator(fieldModel.getFieldTypeConst());
			mv.visitVarInsn(ALOAD, lvIndex);
			mv.visitVarInsn(ALOAD, MODEL_INDEX);
			generator.generateReaderCode(mv);
			mv.visitMethodInsn(INVOKEVIRTUAL, fieldModel.getOwnerDesc(), fieldModel.getSetterName(), fieldModel.getSetterDesc());
		}else{
			createGetObject(fieldModel, mv, lvIndex);
		}
	}
	
	private static void createGetObject(FieldModel fieldModel, MethodVisitor mv, int lvIndex) {
		String checkCast = XsonTypeUtils.getType(fieldModel.getFieldClass());
		mv.visitVarInsn(ALOAD, lvIndex);
		mv.visitVarInsn(ALOAD, MODEL_INDEX);
		mv.visitMethodInsn(INVOKEVIRTUAL, READERMODEL_DESC, "getObject", "()Ljava/lang/Object;");
		mv.visitTypeInsn(CHECKCAST, checkCast);
		mv.visitMethodInsn(INVOKEVIRTUAL, fieldModel.getOwnerDesc(), fieldModel.getSetterName(), fieldModel.getSetterDesc());
	}
	
	public static XsonReader getReader(int typeIndex) {
		XsonReader reader = XsonConst.SYSTEM_READER_MAP.get(typeIndex);
		if (null == reader) {
			throw new XsonDeserializerException("未知的系统Reader索引:" + typeIndex);
		}
		return reader;
	}

	public static XsonReader getReader(Class<?> clazz) {
		XsonReader reader = XsonConst.USER_READER_MAP.get(clazz);
		if (null != reader) {
			return reader;
		}

		if (Map.class.isAssignableFrom(clazz)) {
			return XsonConst.USER_READER_MAP.get(Map.class);
		} else if (Collection.class.isAssignableFrom(clazz)) {
			return XsonConst.USER_READER_MAP.get(Collection.class);
		} else if (clazz.isEnum()) {
			return XsonConst.USER_READER_MAP.get(Enum.class);
		}

		reader = generateInstance(clazz);
		XsonConst.USER_READER_MAP.put(clazz, reader);
		return reader;
	}

	public static XsonReader getSysObjectArrayReader(ReaderModel model) {
		byte[] value = model.getValue();
		int index = model.getIndex();

		int typeIndex = ByteUtils.byteToInt(value[index + 1]);
		int dimensions = ByteUtils.byteToInt(value[index + 2]);

		if (1 == dimensions && XsonSupport.isBasicType(typeIndex)) {
			return XsonConst.SYSTEM_READER_MAP.get(typeIndex
					+ ByteUtils.byteToInt(XsonConst.CONTROL_CREATE_END));
		} else {
			return XsonConst.SYSTEM_READER_MAP.get(OBJECT_ARRAY_KEY);
		}
	}

	public static XsonReader getUserObjectArrayReader() {
		return XsonConst.SYSTEM_READER_MAP.get(OBJECT_ARRAY_KEY);
	}

}
