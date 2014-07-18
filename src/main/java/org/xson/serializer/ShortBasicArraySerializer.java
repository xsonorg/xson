package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class ShortBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		short[] array = (short[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.SHORT, 1, length);
		for (int i = 0; i < length; i++) {
			model.append(XsonSupport.getShortDateFrame2Byte(array[i]));
		}
	}

}