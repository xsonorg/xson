package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class FloatBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		float[] array = (float[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.FLOAT, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeFloat(array[i]);
		}
	}

}
