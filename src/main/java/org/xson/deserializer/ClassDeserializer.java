package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonReader;
import org.xson.util.ByteUtils;
import org.xson.util.XsonTypeUtils;

public class ClassDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int length = ByteUtils.byteToInt(value[model.getIndex() + 1]);
		model.incrementIndex(2);
		String className = new String(value, model.getIndex(), length,
				model.getCharset());
		Class<?> returnValue = XsonTypeUtils.findClass(className);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}
