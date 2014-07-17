package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class DoubleDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Double returnValue = model.getDouble();
		model.appendObject(returnValue);
		return returnValue;
	}
}
