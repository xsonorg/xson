package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class ByteDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Byte returnValue = model.getByte();
		model.appendObject(returnValue);
		return returnValue;
	}

}
