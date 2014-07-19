package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;

public class ClassSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Class<?> clazz = (Class<?>) target;
		byte[] buf = clazz.getName().getBytes(model.getCharset());
		byte[] head = { XsonConst.CLASS, (byte) buf.length };
		model.append(head);
		model.append(buf);
	}

}
