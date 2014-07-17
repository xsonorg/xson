package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class IntegerSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Integer x = (Integer) target;
		byte[] buf = XsonSupport.getIntDateFrame(x.intValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
