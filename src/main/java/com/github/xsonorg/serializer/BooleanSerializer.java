package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonSupport;
/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BooleanSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Boolean x = (Boolean) target;
		byte[] buf = XsonSupport.getBooleanDateFrame(x.booleanValue());
		buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		model.append(buf);
	}

}
