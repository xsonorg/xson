package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

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