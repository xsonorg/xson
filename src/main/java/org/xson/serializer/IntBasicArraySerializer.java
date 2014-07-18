package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

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
