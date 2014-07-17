package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.XsonSupport;

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
