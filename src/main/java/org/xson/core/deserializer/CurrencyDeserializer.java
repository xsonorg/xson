package org.xson.core.deserializer;

import java.util.Currency;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class CurrencyDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		// 系统对象
		model.incrementIndex(1);
		String str = model.getString();
		Currency returnValue = Currency.getInstance(str);
		model.appendObject(returnValue);
		return returnValue;
	}
}