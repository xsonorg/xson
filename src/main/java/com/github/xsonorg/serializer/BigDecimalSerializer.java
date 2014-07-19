package com.github.xsonorg.serializer;

import java.math.BigDecimal;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class BigDecimalSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		BigDecimal bigDecimal = (BigDecimal) target;

		byte[] bigIntData = bigDecimal.unscaledValue().toByteArray();
		int bigIntLength = bigIntData.length;
		int scale = bigDecimal.scale();
		if (256 > bigIntLength && 128 > scale && scale > -129) {
			byte[] head = { XsonConst.BIGDECIMAL1, (byte) scale,
					(byte) bigIntLength };
			model.append(head);
			model.append(bigIntData);
		} else {
			byte[] scaleArray = ByteUtils.intTo4ByteWithMark(scale);
			byte[] intLenArray = ByteUtils.intTo4Byte(bigIntLength);
			scaleArray[0] = XsonConst.BIGDECIMAL;
			model.append(scaleArray);
			model.append(intLenArray);
			model.append(bigIntData);
		}
	}

}
