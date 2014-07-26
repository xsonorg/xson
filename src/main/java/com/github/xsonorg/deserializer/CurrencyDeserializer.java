package com.github.xsonorg.deserializer;

import java.util.Currency;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.util.ByteUtils;

public class CurrencyDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int length = ByteUtils.byteToInt(value[model.getIndex() + 1]);
		model.incrementIndex(2);
		// String str = new String(value, model.getIndex(), length,
		// model.getCharset());
		String str = model.decode(value, model.getIndex(), length);
		Currency returnValue = Currency.getInstance(str);
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}