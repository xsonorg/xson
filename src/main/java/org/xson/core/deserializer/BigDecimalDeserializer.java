package org.xson.core.deserializer;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.xson.core.ReaderModel;
import org.xson.core.XsonConst;
import org.xson.core.XsonReader;
import org.xson.core.util.ByteUtils;

public class BigDecimalDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// 系统对象
		byte[] value = model.getValue();
		int index = model.getIndex();
		byte frameType = value[index];// index-->type
		int length = 0;
		int scale = 0;
		if (frameType == XsonConst.BIGDECIMAL1) {
			scale = ByteUtils.byteToInt(value[index + 1]);
			length = ByteUtils.byteToInt(value[index + 2]);
			model.incrementIndex(3);
		} else {
			model.incrementIndex(1);
			scale = model.getInt();
			length = model.getInt();
		}
		byte[] val = new byte[length];
		System.arraycopy(value, model.getIndex(), val, 0, length);
		BigDecimal returnValue = new BigDecimal(new BigInteger(val), scale);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}
