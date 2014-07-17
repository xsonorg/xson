package org.xson.serializer;

import java.math.BigInteger;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BigIntegerSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		BigInteger bigInteger = (BigInteger) target;
		byte[] buf = bigInteger.toByteArray();
		int length = buf.length;
		if (256 > length) {
			byte[] head = { XsonConst.BIGINTEGER1, (byte) length };
			model.append(head);
		} else {
			model.append(XsonConst.BIGINTEGER);
			model.append(ByteUtils.intTo4Byte(length));
			model.append(buf);
		}
		model.append(buf);
	}
}
