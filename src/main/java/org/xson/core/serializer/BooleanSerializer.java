package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BooleanSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Boolean x = (Boolean) target;
		model.writeBoolean(x.booleanValue(), XsonConst.BASIC_MASK);
	}

}
