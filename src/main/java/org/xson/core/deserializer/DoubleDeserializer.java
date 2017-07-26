package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class DoubleDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Double returnValue = model.getDouble();
		Double returnValue = model.getDoubleWrap();
		model.appendObject(returnValue);
		return returnValue;
	}
}
