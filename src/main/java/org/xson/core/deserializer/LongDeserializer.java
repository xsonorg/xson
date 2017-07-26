package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class LongDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Long returnValue = model.getLong();
		Long returnValue = model.getLongWrap();
		model.appendObject(returnValue);
		return returnValue;
	}
}
