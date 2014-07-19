package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class FloatSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Float x = (Float) target;
		byte[] buf = XsonSupport.getFloatDateFrame(x.floatValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
