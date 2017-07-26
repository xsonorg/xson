package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class BooleanDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Boolean returnValue = model.getBoolean();
		Boolean returnValue = model.getBooleanWrap();
		model.appendObject(returnValue);
		return returnValue;
	}
}
