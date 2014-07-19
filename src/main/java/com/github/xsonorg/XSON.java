package com.github.xsonorg;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XSON {

	public final static String VERSION = "0.0.1";

	public static byte[] write(Object val) {
		return write(val, null);
	}

	public static byte[] write(Object val, String charsetName) {
		if (null == val) {
			return XsonConst.NULL_OBJECT_BYTES;
		}
		ByteModel model = new ByteModel(charsetName);
		model.writeObject(val);
		return model.getData();
	}

	public static byte[] writeByte(byte val) {
		return XsonSupport.getByteDateFrame(val);
	}

	public static byte[] writeShort(short val) {
		return XsonSupport.getShortDateFrame(val);
	}

	public static byte[] writeInt(int val) {
		return XsonSupport.getIntDateFrame(val);
	}

	public static byte[] writeLong(long val) {
		return XsonSupport.getLongDateFrame(val);
	}

	public static byte[] writeFloat(float val) {
		return XsonSupport.getFloatDateFrame(val);
	}

	public static byte[] writeDouble(double val) {
		return XsonSupport.getDoubleDateFrame(val);
	}

	public static byte[] writeBoolean(boolean val) {
		return XsonSupport.getBooleanDateFrame(val);
	}

	public static byte[] writeChar(char val) {
		return XsonSupport.getCharDateFrame(val);
	}

	public static <T> T parse(byte[] val) {
		return parse(val, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T parse(byte[] val, String charsetName) {
		if (null == val || val.length == 1) {
			return null;
		}
		ReaderModel model = new ReaderModel(val, charsetName);
		model.init();
		return (T) model.getObject();
	}

	public static byte parseByte(byte[] val) {
		return parseByte(val, 0);
	}

	public static byte parseByte(byte[] val, int offset) {
		return XsonSupport.getByte(val, offset);
	}

	public static short parseShort(byte[] val) {
		return parseShort(val, 0);
	}

	public static short parseShort(byte[] val, int offset) {
		return XsonSupport.getShort(val, offset);
	}

	public static int parseInt(byte[] val) {
		return parseInt(val, 0);
	}

	public static int parseInt(byte[] val, int offset) {
		return XsonSupport.getInt(val, offset);
	}

	public static long parseLong(byte[] val) {
		return parseLong(val, 0);
	}

	public static long parseLong(byte[] val, int offset) {
		return XsonSupport.getLong(val, offset);
	}

	public static float parseFloat(byte[] val) {
		return parseFloat(val, 0);
	}

	public static float parseFloat(byte[] val, int offset) {
		return XsonSupport.getFloat(val, offset);
	}

	public static double parseDouble(byte[] val) {
		return parseDouble(val, 0);
	}

	public static double parseDouble(byte[] val, int offset) {
		return XsonSupport.getDouble(val, offset);
	}

	public static boolean parseBoolean(byte[] val) {
		return parseBoolean(val, 0);
	}

	public static boolean parseBoolean(byte[] val, int offset) {
		return XsonSupport.getBoolean(val, offset);
	}

	public static char parseChar(byte[] val) {
		return parseChar(val, 0);
	}

	public static char parseChar(byte[] val, int offset) {
		return XsonSupport.getChar(val, offset);
	}

}
