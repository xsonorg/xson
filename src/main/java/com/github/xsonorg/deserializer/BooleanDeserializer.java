package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class BooleanDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Boolean returnValue = model.getBoolean();
		model.appendObject(returnValue);
		return returnValue;
	}
}
