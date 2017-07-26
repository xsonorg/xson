package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;
import org.xson.core.util.XsonTypeUtils;

public class ClassDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		String className = model.getString();
		Class<?> returnValue = XsonTypeUtils.findClass(className);
		model.appendObject(returnValue);
		return returnValue;
	}
}
