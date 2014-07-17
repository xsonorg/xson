package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class LongBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		long[] array = (long[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.LONG, 1, length);
		for (int i = 0; i < length; i++) {
			model.append(XsonSupport.getLongDateFrame(array[i]));
		}
	}

}
