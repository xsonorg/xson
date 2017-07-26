package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class TimestampDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.sql.Timestamp returnValue = new java.sql.Timestamp(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}