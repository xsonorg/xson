package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;

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
