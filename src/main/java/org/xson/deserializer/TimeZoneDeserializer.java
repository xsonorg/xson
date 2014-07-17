package org.xson.deserializer;

import java.util.TimeZone;

import org.xson.ReaderModel;
import org.xson.XsonReader;
import org.xson.util.ByteUtils;

public class TimeZoneDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int length = ByteUtils.byteToInt(value[model.getIndex() + 1]);
		model.incrementIndex(2);
		String str = new String(value, model.getIndex(), length,
				model.getCharset());
		TimeZone returnValue = TimeZone.getTimeZone(str);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}