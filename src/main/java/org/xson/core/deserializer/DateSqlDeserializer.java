package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class DateSqlDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.sql.Date returnValue = new java.sql.Date(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}