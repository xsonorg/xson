package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;

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
