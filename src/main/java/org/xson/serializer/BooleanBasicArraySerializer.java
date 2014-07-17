package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BooleanBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		boolean[] array = (boolean[]) target;
		int length = array.length;
		byte[] buf = new byte[length];
		model.appendCreateSystemArray(XsonConst.BOOLEAN, 1, length);
		for (int i = 0; i < length; i++) {
			buf[i] = (array[i]) ? XsonConst.TRUE : XsonConst.FALSE;
		}
		model.append(buf);
	}
}
