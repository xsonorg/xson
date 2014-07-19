package com.github.xsonorg;

import java.util.Map;

import com.github.xsonorg.deserializer.XsonDeserializerException;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonSupport {

	public static XsonGenerator getXsonGenerator(int key) {
		return XsonConst.GENERATOR_MAP.get(key);
	}

	public static int getFieldTypeConst(Class<?> clazz) {
		Integer classType = XsonConst.FIELD_TYPE_MAP.get(clazz);
		if (null == classType)
			return -1;
		return classType.intValue();
	}

	public static Class<?> getSystemType(int key) {
		return XsonConst.SYSTEM_TYPE_MAP.get(key);
	}

	public static boolean isBasicType(int type) {
		if (type > 0 && type < 31) {
			return true;
		}
		return false;
	}

	public static byte[] getByteDateFrame(byte x) {
		byte[] frame = { XsonConst.BYTE, x };
		return frame;
	}

	public static byte[] getBooleanDateFrame(boolean x) {
		byte[] frame = { XsonConst.BOOLEAN,
				(x) ? XsonConst.TRUE : XsonConst.FALSE };
		return frame;
	}

	public static byte[] getShortDateFrame(short x) {
		return ByteUtils.shortToByteWithMark(x);
	}

	public static byte[] getShortDateFrame2Byte(short x) {
		return ByteUtils.shortTo2Byte(x);
	}

	public static byte[] getLongDateFrame(long x) {
		return ByteUtils.longToByteWithMark(x);
	}

	public static byte[] getFloatDateFrame(float x) {
		return ByteUtils.floatToByteWithMark(x);
	}

	public static byte[] getDoubleDateFrame(double x) {
		return ByteUtils.doubleToByteWithMark(x);
	}

	public static byte[] getCharDateFrame(char x) {
		return ByteUtils.charToByteWithMark(x);
	}

	public static byte[] getCharDateFrame2Byte(char x) {
		return ByteUtils.charTo2Byte(x);
	}

	public static byte[] getIntDateFrame(int x) {
		return ByteUtils.intToByteWithMark(x);
	}

	// //////////////////////////////////////////////

	public static byte getByte(byte[] value, int index) {
		byte b = value[index + 1];
		return b;
	}

	public static short getShort(byte[] value, int index) {
		short i = 0;
		byte b = value[index++];
		switch (b) {
		case XsonConst.SHORT1:
			i = ByteUtils.byteToShort(value, index, 1);
			break;
		case XsonConst.SHORT:
			i = ByteUtils.byteToShort(value, index, 2);
			break;
		default:
			throw new XsonDeserializerException("Illegal short mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index " + index);
		}
		return i;
	}

	public static int getInt(byte[] value, int index) {
		int i = 0;
		byte b = value[index++];
		switch (b) {
		case XsonConst.INT1:
			i = ByteUtils.byteToInt(value, index, 1);
			break;
		case XsonConst.INT2:
			i = ByteUtils.byteToInt(value, index, 2);
			break;
		case XsonConst.INT3:
			i = ByteUtils.byteToInt(value, index, 3);
			break;
		case XsonConst.INT:
			i = ByteUtils.byteToInt(value, index, 4);
			break;
		default:
			throw new XsonDeserializerException("Illegal int mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index " + index);
		}
		return i;
	}

	public static long getLong(byte[] value, int index) {
		long i = 0L;
		byte b = value[index++];
		switch (b) {
		case XsonConst.LONG1:
			i = ByteUtils.byteToLong(value, index, 1);
			break;
		case XsonConst.LONG2:
			i = ByteUtils.byteToLong(value, index, 2);
			break;
		case XsonConst.LONG3:
			i = ByteUtils.byteToLong(value, index, 3);
			break;
		case XsonConst.LONG4:
			i = ByteUtils.byteToLong(value, index, 4);
			break;
		case XsonConst.LONG5:
			i = ByteUtils.byteToLong(value, index, 5);
			break;
		case XsonConst.LONG6:
			i = ByteUtils.byteToLong(value, index, 6);
			break;
		case XsonConst.LONG7:
			i = ByteUtils.byteToLong(value, index, 7);
			break;
		case XsonConst.LONG:
			i = ByteUtils.byteToLong(value, index, 8);
			break;
		default:
			throw new XsonDeserializerException("Illegal long mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index " + index);
		}
		return i;
	}

	public static float getFloat(byte[] value, int index) {
		float i = 0;
		byte b = value[index++];
		switch (b) {
		case XsonConst.FLOAT1:
			i = ByteUtils.byteToFloat(value, index, 1);
			break;
		case XsonConst.FLOAT2:
			i = ByteUtils.byteToFloat(value, index, 2);
			break;
		case XsonConst.FLOAT3:
			i = ByteUtils.byteToFloat(value, index, 3);
			break;
		case XsonConst.FLOAT:
			i = ByteUtils.byteToFloat(value, index, 4);
			break;
		default:
			throw new XsonDeserializerException("Illegal float mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index " + index);
		}
		return i;
	}

	public static double getDouble(byte[] value, int index) {
		double i = 0D;
		byte b = value[index++];
		switch (b) {
		case XsonConst.DOUBLE1:
			i = ByteUtils.byteToDouble(value, index, 1);
			break;
		case XsonConst.DOUBLE2:
			i = ByteUtils.byteToDouble(value, index, 2);
			break;
		case XsonConst.DOUBLE3:
			i = ByteUtils.byteToDouble(value, index, 3);
			break;
		case XsonConst.DOUBLE4:
			i = ByteUtils.byteToDouble(value, index, 4);
			break;
		case XsonConst.DOUBLE5:
			i = ByteUtils.byteToDouble(value, index, 5);
			break;
		case XsonConst.DOUBLE6:
			i = ByteUtils.byteToDouble(value, index, 6);
			break;
		case XsonConst.DOUBLE7:
			i = ByteUtils.byteToDouble(value, index, 7);
			break;
		case XsonConst.DOUBLE:
			i = ByteUtils.byteToDouble(value, index, 8);
			break;
		default:
			throw new XsonDeserializerException("Illegal double mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index " + index);
		}
		return i;
	}

	public static boolean getBoolean(byte[] value, int index) {
		byte b = value[index + 1];
		if (b == XsonConst.TRUE) {
			return true;
		}
		return false;
	}

	public static char getChar(byte[] value, int index) {
		char i;
		byte b = value[index++];
		switch (b) {
		case XsonConst.CHAR1:
			i = ByteUtils.byteToChar(value, index, 1);
			break;
		case XsonConst.CHAR:
			i = ByteUtils.byteToChar(value, index, 2);
			break;
		default:
			throw new XsonDeserializerException("Illegal char mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index " + index);
		}
		return i;
	}

	// //////////////////////////////////////////////

	/**
	 * 添加用户自定义的Reader和Writer
	 */
	public static void addCustomSupportType(Class<?> clazz, XsonWriter writer,
			XsonReader reader) throws XsonException {
		if (null == clazz) {
			throw new XsonException("clazz Can not be null");
		}

		if (null == writer && null == reader) {
			throw new XsonException("Writer and Reader Can not be null");
		}

		if (null != writer && null == XsonConst.WRITER_MAP.get(clazz)) {
			XsonConst.WRITER_MAP.put(clazz, writer);
		}

		if (null != reader && null == XsonConst.USER_READER_MAP.get(clazz)) {
			XsonConst.USER_READER_MAP.put(clazz, reader);
		}
	}

	/**
	 * 添加用户自定义的的协定类型
	 */
	public static void addCustomAgreementType(Map<String, String> prop)
			throws XsonException {
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
					throw new XsonException(
							"Type found in already existing : type = '" + key
									+ "', old key = " + oldVal + ", new key = "
									+ val);
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
