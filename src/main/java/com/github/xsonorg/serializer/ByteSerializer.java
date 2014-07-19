package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Byte x = (Byte) target;
		byte[] buf = XsonSupport.getByteDateFrame(x.byteValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
