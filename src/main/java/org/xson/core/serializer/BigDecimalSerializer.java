package org.xson.core.serializer;

import java.math.BigDecimal;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BigDecimalSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		BigDecimal bigDecimal = (BigDecimal) target;
		byte[] bigIntData = bigDecimal.unscaledValue().toByteArray();
		int bigIntLength = bigIntData.length;
		int scale = bigDecimal.scale();
		if (256 > bigIntLength && ((scale & 0xFFFFFF00) == 0)) {
			model.writeByte1(XsonConst.BIGDECIMAL1);
			model.writeByte1((byte) scale);
			model.writeByte1((byte) bigIntLength);
			model.writeByteArray(bigIntData, false);
		} else {
			model.writeByte1(XsonConst.BIGDECIMAL);
			model.writeInt(scale);
			model.writeInt(bigIntLength);
			model.writeByteArray(bigIntData, false);
		}
	}

}
