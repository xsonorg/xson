package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonWriter;

public class DefaultSerializer implements XsonWriter {

	@Override
	public void write(Object target, WriterModel model) {
		throw new XsonSerializerException("Subclasses need to implement");
	}
}
