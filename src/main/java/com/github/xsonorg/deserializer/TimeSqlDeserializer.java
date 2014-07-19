package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class TimeSqlDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.sql.Time returnValue = new java.sql.Time(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}