package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class CharDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Character returnValue = model.getChar();
		model.appendObject(returnValue);
		return returnValue;
	}
}
