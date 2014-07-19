package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class DoubleBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		double[] array = (double[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.DOUBLE, 1, length);
		for (int i = 0; i < length; i++) {
			model.append(XsonSupport.getDoubleDateFrame(array[i]));
		}
	}

}
