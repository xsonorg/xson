package com.github.xsonorg.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonException;
import com.github.xsonorg.asm.ClassWriter;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonTypeUtils {

	private static XsonClassLoader classLoader = new XsonClassLoader();

	public static char ARRAY_MARK = '[';

	public static String toAsmClass(String className) {
		return className.replace('.', '/');
	}

	public static String getLittleClassName(String fullName) {
		int index = fullName.lastIndexOf(".");
		if (index > 0) {
			return fullName.substring(index + 1);
		} else {
			return fullName;
		}
	}

	public static List<FieldModel> getFieldModels(Class<?> clazz) {
		List<FieldModel> fieldInfoList = XsonConst.FieldModel_Cache.get(clazz);
		if (null != fieldInfoList) {
			return fieldInfoList;
		}
		fieldInfoList = getFieldModels0(clazz);
		XsonConst.FieldModel_Cache.put(clazz, fieldInfoList);
		return fieldInfoList;
	}

	private static List<FieldModel> getFieldModels0(Class<?> clazz) {

		List<Field> fieldList = getFieldList(clazz);

		Map<String, Method> methodMap = getMethodMap(clazz);

		List<FieldModel> fieldInfoList = new ArrayList<FieldModel>();

		int size = fieldList.size();
		for (int i = 0; i < size; i++) {
			Field field = fieldList.get(i);
			if (checkField(field)) {
				continue;
			}
			String fieldName = field.getName();

			String getName = getFormatMethodName("get", fieldName);
			Method getter = methodMap.get(getName);

			if (null == getter) {
				getName = getFormatMethodName("is", fieldName);
				getter = methodMap.get(getName);
				if (null == getter) {
					continue;
				}
			}

			if (checkGetter(getter)) {
				continue;
			}

			String setName = getFormatMethodName("set", fieldName);
			Method setter = methodMap.get(setName);
			if (null == setter) {
				continue;
			}

			if (checkSetter(setter, field)) {
				continue;
			}

			fieldInfoList.add(new FieldModel(field, getter, setter, clazz));
		}

		Collections.sort(fieldInfoList);
		return fieldInfoList;
	}

	private static String getFormatMethodName(String prefix, String name) {
		if (1 == name.length()) {
			return prefix + name.toUpperCase();
		} else {
			return prefix + name.substring(0, 1).toUpperCase()
					+ name.substring(1);
		}
	}

	private static boolean checkField(Field field) {
		int modifiers = field.getModifiers();
		if (Modifier.isFinal(modifiers)) {
			return true;
		}
		if (Modifier.isTransient(modifiers)) {
			return true;
		}
		if (Modifier.isStatic(modifiers)) {
			return true;
		}
		return false;
	}

	private static boolean checkGetter(Method method) {
		int modifiers = method.getModifiers();
		if (Modifier.isStatic(modifiers)) {
			return true;
		}
		if (Modifier.isNative(modifiers)) {
			return true;
		}
		if (Modifier.isAbstract(modifiers)) {
			return true;
		}
		if (method.getReturnType().equals(Void.TYPE)) {
			return true;
		}
		if (method.getParameterTypes().length != 0) {
			return true;
		}
		if (method.getReturnType() == ClassLoader.class) {
			return true;
		}
		if (method.getName().equals("getClass")) {
			return true;
		}
		return false;
	}

	private static boolean checkSetter(Method method, Field field) {
		int modifiers = method.getModifiers();
		if (Modifier.isStatic(modifiers)) {
			return true;
		}
		if (Modifier.isNative(modifiers)) {
			return true;
		}
		if (Modifier.isAbstract(modifiers)) {
			return true;
		}
		if (!method.getReturnType().equals(Void.TYPE)) {
			return true;
		}
		if (method.getParameterTypes().length != 1) {
			return true;
		}
		if (!method.getParameterTypes()[0].equals(field.getType())) {
			return true;
		}
		return false;
	}

	private static List<Field> getFieldList(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		Class<?> currentClazz = clazz;
		do {
			Field[] fields = currentClazz.getDeclaredFields();
			for (Field item : fields) {
				fieldList.add(item);
			}
			currentClazz = currentClazz.getSuperclass();
		} while (currentClazz != null && currentClazz != Object.class);
		return fieldList;
	}

	public static Map<String, Method> getMethodMap(Class<?> clazz) {
		Map<String, Method> methodMap = new HashMap<String, Method>();
		Method[] methods = clazz.getMethods();
		int length = methods.length;
		for (int i = 0; i < length; i++) {
			methodMap.put(methods[i].getName(), methods[i]);
		}
		return methodMap;
	}

	public static int getArrayDimensions(Class<?> clazz) {
		String var = clazz.getName();
		char[] data = var.toCharArray();
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			if (ARRAY_MARK == data[i]) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public static String[] getExceptionDesc(Method method) {
		Class<?>[] exClasses = method.getExceptionTypes();
		if (null != exClasses && exClasses.length > 0) {
			String[] exClassesName = new String[exClasses.length];
			for (int i = 0; i < exClasses.length; i++) {
				exClassesName[i] = toAsmClass(exClasses[i].getName());
			}
			return exClassesName;
		}
		return null;
	}

	public static String getDesc(Method method) {
		StringBuffer buf = new StringBuffer();
		buf.append("(");
		java.lang.Class<?>[] types = method.getParameterTypes();
		for (int i = 0; i < types.length; ++i) {
			buf.append(getDesc(types[i]));
		}
		buf.append(")");
		buf.append(getDesc(method.getReturnType()));
		return buf.toString();
	}

	public static String getDesc(Class<?> returnType) {
		if (returnType.isPrimitive()) {
			return getPrimitiveLetter(returnType);
		} else if (returnType.isArray()) {
			return "[" + getDesc(returnType.getComponentType());
		} else {
			return "L" + getType(returnType) + ";";
		}
	}

	public static String getType(Class<?> parameterType) {
		if (parameterType.isArray()) {
			return "[" + getDesc(parameterType.getComponentType());
		} else {
			if (!parameterType.isPrimitive()) {
				String clsName = parameterType.getName();
				return clsName.replaceAll("\\.", "/");
			} else {
				return getPrimitiveLetter(parameterType);
			}
		}
	}

	public static String getPrimitiveLetter(Class<?> type) {
		if (Integer.TYPE.equals(type)) {
			return "I";
		} else if (Void.TYPE.equals(type)) {
			return "V";
		} else if (Boolean.TYPE.equals(type)) {
			return "Z";
		} else if (Character.TYPE.equals(type)) {
			return "C";
		} else if (Byte.TYPE.equals(type)) {
			return "B";
		} else if (Short.TYPE.equals(type)) {
			return "S";
		} else if (Float.TYPE.equals(type)) {
			return "F";
		} else if (Long.TYPE.equals(type)) {
			return "J";
		} else if (Double.TYPE.equals(type)) {
			return "D";
		}
		throw new IllegalStateException("Type: " + type.getCanonicalName()
				+ " is not a primitive type");
	}

	public static String getComponentDesc(Class<?> type, int dimension) {
		String brackets = "";
		int count = dimension;
		while (count-- > 1) {
			brackets += "[";
		}
		String clsName = null;
		if (type.isPrimitive()) {
			clsName = getPrimitiveLetter(type);
		} else {
			clsName = "L" + type.getName() + ";";
		}
		return brackets + clsName;
	}

	public static Class<?> getArrayType(Class<?> componentType, int dimension) {
		if (1 == dimension) {
			return componentType;
		} else if (dimension > 1) {
			// return loadClass(getComponentDesc(componentType, dimension));
			return findClass(getComponentDesc(componentType, dimension));
		} else {
			throw new XsonException("Unlawful dimension：" + dimension);
		}
	}

	public static Class<?> getComponentType(Class<?> type) {
		if (type.isArray()) {
			return getComponentType(type.getComponentType());
		}
		return type;
	}

	public static Class<?> findClass(String className) {
		Throwable prob = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader != null) {
			try {
				return Class.forName(className, true, loader);
			} catch (Exception e) {
				prob = e;
			}
		}
		try {
			return Class.forName(className);
		} catch (Exception e) {
			prob = e;
		}

		throw new XsonException(prob);
	}

	public static Object createByteCode(ClassWriter classWriter,
			String className) throws Exception {
		byte[] classCode = classWriter.toByteArray();

		// logger
		// writeClass(classCode, className);
		// System.out.println("createByteCode:" + className);

		Class<?> exampleClass = classLoader.defineClassPublic(className,
				classCode, 0, classCode.length);
		Object instance = exampleClass.newInstance();
		return instance;
	}

	public static void writeClass(byte[] code, String className) {
		String classPath = "C:/Users/david/Desktop/aaadd/code/";
		try {
			FileOutputStream fos = new FileOutputStream(classPath + className
					+ ".class");
			fos.write(code);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
