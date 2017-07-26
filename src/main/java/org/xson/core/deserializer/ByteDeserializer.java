package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class ByteDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Byte returnValue = model.getByte();
		Byte returnValue = model.getByteWrap();
		model.appendObject(returnValue);
		return returnValue;
	}

}
