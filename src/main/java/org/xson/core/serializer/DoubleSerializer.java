package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class DoubleSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Double x = (Double) target;
		model.writeDouble(x.doubleValue(), XsonConst.BASIC_MASK);
	}

}
