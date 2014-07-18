package org.xson.deserializer;

import java.util.UUID;

import org.xson.ReaderModel;
import org.xson.XsonReader;

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