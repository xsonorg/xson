package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class CharDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// model.wrapToBasic();
		// Character returnValue = model.getChar();
		Character returnValue = model.getCharWrap();
		model.appendObject(returnValue);
		return returnValue;
	}
}
