package com.github.xsonorg.deserializer;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.util.ByteUtils;

public class BigDecimalDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
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
			scale = ByteUtils.byteToInt(value, index + 1, 4);
			length = ByteUtils.byteToInt(value, index + 5, 4);
			model.incrementIndex(9);
		}
		byte[] val = new byte[length];
		System.arraycopy(value, model.getIndex(), val, 0, length);
		BigDecimal returnValue = new BigDecimal(new BigInteger(val), scale);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}
