package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class DoubleSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Double x = (Double) target;
		byte[] buf = XsonSupport.getDoubleDateFrame(x.doubleValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
