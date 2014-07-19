package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class FloatDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Float returnValue = model.getFloat();
		model.appendObject(returnValue);
		return returnValue;
	}
}
