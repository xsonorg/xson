package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class ShortSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Short x = (Short) target;
		byte[] buf = XsonSupport.getShortDateFrame(x.shortValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
