package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class FloatDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Float returnValue = model.getFloat();
		model.appendObject(returnValue);
		return returnValue;
	}
}
