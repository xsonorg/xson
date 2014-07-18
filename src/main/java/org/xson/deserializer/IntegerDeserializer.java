package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class IntegerDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Integer returnValue = model.getInt();
		model.appendObject(returnValue);
		return returnValue;
	}
}
