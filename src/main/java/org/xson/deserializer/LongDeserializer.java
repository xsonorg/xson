package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class LongDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Long returnValue = model.getLong();
		model.appendObject(returnValue);
		return returnValue;
	}
}
