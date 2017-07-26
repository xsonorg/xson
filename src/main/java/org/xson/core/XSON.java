package org.xson.core;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XSON {

	public final static String VERSION = "1.0.2";

	public static byte[] encode(Object val) {
		return encode(val, 0);
	}

	public static byte[] encode(Object val, int offsetLength) {
		if (null == val) {
			byte[] result = new byte[offsetLength + 1];
			result[offsetLength] = XsonConst.NULL;
			return result;
		}
		WriterModel model = new WriterModel(offsetLength);
		model.writeObject(val);
		return model.getData();
	}

	public static <T> T decode(byte[] val) {
		return decode(val, 0);
	}

	@SuppressWarnings("unchecked")
	public static <T> T decode(byte[] val, int offsetLength) {
		if (null == val || val.length <= offsetLength + 1) {
			return null;
		}
		ReaderModel model = new ReaderModel(val, offsetLength);
		model.init();
		return (T) model.getObject();
	}
}
