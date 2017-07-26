package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class DoubleBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		double[] array = (double[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.DOUBLE, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeDouble(array[i]);
		}
	}

}
