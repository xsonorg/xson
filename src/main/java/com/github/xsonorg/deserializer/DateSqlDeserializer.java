package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class DateSqlDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.sql.Date returnValue = new java.sql.Date(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}