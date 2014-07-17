package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class DateUtilDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.util.Date returnValue = new java.util.Date(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}