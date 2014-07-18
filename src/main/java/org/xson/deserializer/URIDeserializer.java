package org.xson.deserializer;

import java.net.URI;

import org.xson.ReaderModel;
import org.xson.XsonConst;
import org.xson.XsonReader;
import org.xson.util.ByteUtils;

public class URIDeserializer implements XsonReader {
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
		default:// 4byte
			throw new XsonDeserializerException("Not supported URI type "
					+ frameType + " at index " + (model.getIndex() - 1));
		}

		URI returnValue = URI.create(new String(value, model.getIndex(),
				length, model.getCharset()));
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}