package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BooleanBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		boolean[] array = (boolean[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.BOOLEAN, 1, length);
		for (int i = 0; i < length; i++) {
			model.writeByte1((array[i]) ? XsonConst.TRUE : XsonConst.FALSE);
		}
	}

}
