package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class LongSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Long x = (Long) target;
		model.writeLong(x.longValue(), XsonConst.BASIC_MASK);
	}
}
