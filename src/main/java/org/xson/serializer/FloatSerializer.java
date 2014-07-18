package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class FloatSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Float x = (Float) target;
		byte[] buf = XsonSupport.getFloatDateFrame(x.floatValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
