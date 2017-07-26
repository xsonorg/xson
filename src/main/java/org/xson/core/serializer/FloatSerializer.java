package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class FloatSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Float x = (Float) target;
		model.writeFloat(x.floatValue(), XsonConst.BASIC_MASK);
	}

}
