package org.xson.core.deserializer;

import java.util.TimeZone;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class TimeZoneDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		String ID = model.getString();
		TimeZone returnValue = TimeZone.getTimeZone(ID);
		model.appendObject(returnValue);
		return returnValue;
	}
}