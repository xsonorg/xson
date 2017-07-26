package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class StringDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		String returnValue = model.getString();
		model.appendObject(returnValue);
		return returnValue;
	}

}
