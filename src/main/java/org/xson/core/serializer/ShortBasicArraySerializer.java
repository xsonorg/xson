package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class ShortBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		short[] array = (short[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.SHORT, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeShort2(array[i]);
		}
	}

}