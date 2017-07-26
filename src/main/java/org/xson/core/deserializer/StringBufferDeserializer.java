package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class StringBufferDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		String str = model.getString();
		StringBuffer returnValue = new StringBuffer(str);
		model.appendObject(returnValue);
		return returnValue;
	}
}
