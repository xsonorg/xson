package org.xson.core;

import java.util.Map;

import org.xson.core.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonSupport {

	public static int getFieldTypeConst(Class<?> clazz) {
		Integer classType = XsonConst.FIELD_TYPE_MAP.get(clazz);
		if (null == classType)
			return -1;
		return classType.intValue();
	}

	public static boolean isBasicType(int type) {
		if (type > 0 && type < 31) {
			return true;
		}
		return false;
	}

	// //////////////////////////////////////////////

	/**
	 * 添加用户自定义的Reader和Writer
	 */
	public static void addCustomSupportType(Class<?> clazz, XsonWriter writer, XsonReader reader) throws XsonException {
		if (null == clazz) {
			throw new XsonException("clazz Can not be null");
		}
		if (null == writer && null == reader) {
			throw new XsonException("Writer and Reader Can not be null");
		}
		if (XsonConst.WRITER_MAP.containsKey(clazz)) {
			throw new XsonException("Writer already exists: " + clazz.getName());
		}
		if (XsonConst.USER_READER_MAP.containsKey(clazz)) {
			throw new XsonException("Reader already exists: " + clazz.getName());
		}
		XsonConst.WRITER_MAP.put(clazz, writer);
		XsonConst.USER_READER_MAP.put(clazz, reader);
	}

	/** 添加用户自定义序列化器 */
	public static void addCustomSerializer(Class<?> clazz, XsonWriter writer, XsonReader reader) throws XsonException {
		if (null == clazz) {
			throw new XsonException("clazz Can not be null");
		}
		if (null == writer && null == reader) {
			throw new XsonException("Writer and Reader Can not be null");
		}
		if (XsonConst.WRITER_MAP.containsKey(clazz)) {
			throw new XsonException("Writer already exists: " + clazz.getName());
		}
		if (XsonConst.USER_READER_MAP.containsKey(clazz)) {
			throw new XsonException("Reader already exists: " + clazz.getName());
		}
		XsonConst.WRITER_MAP.put(clazz, writer);
		XsonConst.USER_READER_MAP.put(clazz, reader);
	}

	/**
	 * 添加用户自定义的的协定类型
	 */
	public static void addCustomAgreementType(Map<String, String> prop) throws XsonException {
		if (null == prop) {
			return;
		}
		try {
			for (Map.Entry<String, String> entry : prop.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();

				if (null == key || null == val) {
					throw new XsonException("key or val Can not be null");
				}
				key = key.trim();
				val = val.trim();
				if (0 == key.length() || 0 == val.length()) {
					throw new XsonException("key or val Can not be empty");
				}
				Class<?> clazz = XsonTypeUtils.findClass(key);
				String oldVal = XsonConst.CUSTOM_AGREEMENT_TYPE_MAP.get(clazz);
				if (null == oldVal) {
					XsonConst.CUSTOM_AGREEMENT_TYPE_MAP.put(clazz, val);
					XsonConst.CUSTOM_AGREEMENT_KEY_MAP.put(val, clazz);
				} else if (!oldVal.equals(val)) {
					throw new XsonException("Type found in already existing : type = '" + key + "', old key = " + oldVal + ", new key = " + val);
				}
			}
		} catch (Exception e) {
			throw new XsonException(e);
		}
	}

	/**
	 * 获取用户约定类型标识
	 */
	public static String getCustomAgreementKey(Class<?> type) {
		return XsonConst.CUSTOM_AGREEMENT_TYPE_MAP.get(type);
	}

	/**
	 * 获取用户约定类型
	 */
	public static Class<?> getCustomAgreementType(String key) {
		return XsonConst.CUSTOM_AGREEMENT_KEY_MAP.get(key);
	}

}
