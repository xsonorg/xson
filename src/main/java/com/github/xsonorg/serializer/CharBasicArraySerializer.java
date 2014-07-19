package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

public class CharBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		char[] array = (char[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.CHAR, 1, length);
		for (int i = 0; i < length; i++) {
			model.append(XsonSupport.getCharDateFrame2Byte(array[i]));
		}
	}

}