package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.codecs.CharsetUtils;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.XsonTypeUtils;

public class ClassDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int length = ByteUtils.byteToInt(value[model.getIndex() + 1]);
		model.incrementIndex(2);
		// String className = new String(value, model.getIndex(), length,
		// model.getCharset());
		String className = model.decode(value, model.getIndex(), length,
				CharsetUtils.ASCII);
		Class<?> returnValue = XsonTypeUtils.findClass(className);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}
