package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

public class FloatBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		float[] array = (float[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.FLOAT, 1, length);
		for (int i = 0; i < length; i++) {
			model.append(XsonSupport.getFloatDateFrame(array[i]));
		}
	}

}
