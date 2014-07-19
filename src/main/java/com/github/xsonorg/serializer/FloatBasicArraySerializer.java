package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

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
