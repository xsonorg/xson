package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class IntBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		int[] array = (int[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.INT, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeInt(array[i]);
		}
	}

}
