package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonWriter;

public class DefaultSerializer implements XsonWriter {

	@Override
	public void write(Object target, ByteModel model) {
		throw new XsonSerializerException("Subclasses need to implement");
	}
}
