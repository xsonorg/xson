package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.util.ByteUtils;

public class StringBufferDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		byte frameType = value[model.getIndex() + 1];
		model.incrementIndex(2);
		int length = 0;
		switch (frameType) {
		case XsonConst.STRING1:// 1byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 1);
			model.incrementIndex(1);
			break;
		case XsonConst.STRING2:// 2byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 2);
			model.incrementIndex(2);
			break;
		case XsonConst.STRING3:// 3byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 3);
			model.incrementIndex(3);
			break;
		case XsonConst.STRING:// 4byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 4);
			model.incrementIndex(4);
			break;
		case XsonConst.NULL:
			StringBuffer returnValue = new StringBuffer(0);
			model.appendObject(returnValue);
			return returnValue;
		default:// 4byte
			throw new XsonDeserializerException(
					"Not supported StringBuffer type " + frameType
							+ " at index " + (model.getIndex() - 1));
		}
		// StringBuffer returnValue = new StringBuffer(new String(value,
		// model.getIndex(), length, model.getCharset()));
		StringBuffer returnValue = new StringBuffer(model.decode(value,
				model.getIndex(), length));
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}
