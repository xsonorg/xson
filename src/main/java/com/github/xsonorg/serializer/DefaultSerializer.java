package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonWriter;

public class DefaultSerializer implements XsonWriter {

	@Override
	public void write(Object target, ByteModel model) {
		throw new XsonSerializerException("Subclasses need to implement");
	}
}
