package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class IntegerDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Integer returnValue = model.getInt();
		Integer returnValue = model.getIntWrap();
		model.appendObject(returnValue);
		return returnValue;
	}
}
