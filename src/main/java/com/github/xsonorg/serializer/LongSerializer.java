package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class LongSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Long x = (Long) target;
		byte[] buf = XsonSupport.getLongDateFrame(x.longValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}
}
