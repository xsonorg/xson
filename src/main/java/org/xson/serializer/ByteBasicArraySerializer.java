package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		byte[] array = (byte[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.BYTE, 1, length);
		model.append(array);
	}

}
