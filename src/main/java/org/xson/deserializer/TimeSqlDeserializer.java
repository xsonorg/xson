package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class TimeSqlDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.sql.Time returnValue = new java.sql.Time(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}