package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class CharBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		char[] array = (char[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.CHAR, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeChar2(array[i]);
		}
	}

}