package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteBasicArraySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		byte[] array = (byte[]) target;
		int length = array.length;
		model.appendCreateSystemArray(XsonConst.BYTE, 1, length);
		model.writeByteArray(array, false);
	}

}
