package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.codecs.CharsetUtils;

public class ClassSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Class<?> clazz = (Class<?>) target;
		// byte[] buf = clazz.getName().getBytes(model.getCharset());
		byte[] buf = model.encode(clazz.getName(), CharsetUtils.ASCII);
		byte[] head = { XsonConst.CLASS, (byte) buf.length };
		model.append(head);
		model.append(buf);
	}

}
