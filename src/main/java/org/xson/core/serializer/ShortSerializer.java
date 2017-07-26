package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class ShortSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Short x = (Short) target;
		model.writeShort(x.shortValue(), XsonConst.BASIC_MASK);
	}

}
