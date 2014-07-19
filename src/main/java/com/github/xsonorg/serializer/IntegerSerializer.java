package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class IntegerSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Integer x = (Integer) target;
		byte[] buf = XsonSupport.getIntDateFrame(x.intValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
