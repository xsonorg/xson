package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class ShortDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.wrapToBasic();
		Short returnValue = model.getShort();
		model.appendObject(returnValue);
		return returnValue;
	}
}
