package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class IntBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		int[] array = (int[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.INT, 1, length);
		for (int i = 0; i < length; i++) {
			model.append(XsonSupport.getIntDateFrame(array[i]));
		}
	}

}
