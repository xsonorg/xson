package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class IntegerSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Integer x = (Integer) target;
		model.writeInt(x.intValue(), XsonConst.BASIC_MASK);
	}

}
