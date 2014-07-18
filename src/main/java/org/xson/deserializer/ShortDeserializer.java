package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class ShortDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Short returnValue = model.getShort();
		model.appendObject(returnValue);
		return returnValue;
	}
}
