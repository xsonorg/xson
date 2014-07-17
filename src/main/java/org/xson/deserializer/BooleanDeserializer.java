package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class BooleanDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Boolean returnValue = model.getBoolean();
		model.appendObject(returnValue);
		return returnValue;
	}
}
