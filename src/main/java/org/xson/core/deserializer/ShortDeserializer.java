package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class ShortDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Short returnValue = model.getShort();
		Short returnValue = model.getShortWrap();
		model.appendObject(returnValue);
		return returnValue;
	}
}
