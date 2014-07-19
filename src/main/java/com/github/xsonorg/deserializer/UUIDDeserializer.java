package com.github.xsonorg.deserializer;

import java.util.UUID;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class UUIDDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		long mostSigBits = model.getLong();
		long leastSigBits = model.getLong();
		UUID returnValue = new UUID(mostSigBits, leastSigBits);
		model.appendObject(returnValue);
		return returnValue;
	}
}