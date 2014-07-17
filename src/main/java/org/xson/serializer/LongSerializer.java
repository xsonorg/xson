package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class LongSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Long x = (Long) target;
		byte[] buf = XsonSupport.getLongDateFrame(x.longValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}
}
