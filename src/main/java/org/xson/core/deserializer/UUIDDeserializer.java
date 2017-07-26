package org.xson.core.deserializer;

import java.util.UUID;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

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