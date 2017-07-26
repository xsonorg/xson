package org.xson.core.serializer;

import java.util.Currency;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class CurrencySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Currency currency = (Currency) target;
		String x = currency.getCurrencyCode();
		model.writeByte1(XsonConst.CURRENCY);
		model.writeString(x);
	}
}