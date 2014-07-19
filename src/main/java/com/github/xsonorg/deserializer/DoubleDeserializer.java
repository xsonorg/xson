package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class DoubleDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Double returnValue = model.getDouble();
		model.appendObject(returnValue);
		return returnValue;
	}
}
