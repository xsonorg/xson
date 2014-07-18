package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class DoubleSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Double x = (Double) target;
		byte[] buf = XsonSupport.getDoubleDateFrame(x.doubleValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
