package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class ShortSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Short x = (Short) target;
		byte[] buf = XsonSupport.getShortDateFrame(x.shortValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
