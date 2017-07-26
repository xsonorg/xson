package org.xson.core.serializer;

import java.math.BigInteger;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BigIntegerSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		BigInteger bigInteger = (BigInteger) target;
		byte[] buf = bigInteger.toByteArray();
		int length = buf.length;
		if (256 > length) {
			model.writeByte1(XsonConst.BIGINTEGER1);
			model.writeByte1((byte) length);
		} else {
			model.writeByte1(XsonConst.BIGINTEGER);
			model.writeInt(length);
		}
		model.writeByteArray(buf, false);
	}
}
