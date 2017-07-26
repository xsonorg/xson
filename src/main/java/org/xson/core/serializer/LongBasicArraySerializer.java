package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class LongBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		long[] array = (long[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.LONG, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeLong(array[i]);
		}
	}

}
