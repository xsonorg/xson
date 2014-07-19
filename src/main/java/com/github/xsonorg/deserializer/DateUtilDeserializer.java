package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class DateUtilDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		java.util.Date returnValue = new java.util.Date(model.getLong());
		model.appendObject(returnValue);
		return returnValue;
	}
}