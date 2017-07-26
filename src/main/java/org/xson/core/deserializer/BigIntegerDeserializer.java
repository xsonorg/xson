package org.xson.core.deserializer;

import java.math.BigInteger;

import org.xson.core.ReaderModel;
import org.xson.core.XsonConst;
import org.xson.core.XsonReader;
import org.xson.core.util.ByteUtils;

public class BigIntegerDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int index = model.getIndex();
		byte frameType = value[index];// index-->type
		int length = 0;
		if (frameType == XsonConst.BIGINTEGER1) {
			length = ByteUtils.byteToInt(value[index + 1]);
			model.incrementIndex(2);
		} else {
			model.incrementIndex(1);
			length = model.getInt();
		}
		byte[] val = new byte[length];
		System.arraycopy(value, model.getIndex(), val, 0, length);
		BigInteger returnValue = new BigInteger(val);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}
