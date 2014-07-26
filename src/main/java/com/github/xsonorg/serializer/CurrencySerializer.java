package com.github.xsonorg.serializer;

import java.util.Currency;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;

public class CurrencySerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Currency currency = (Currency) target;
		String x = currency.getCurrencyCode();
		// byte[] buf = x.getBytes(model.getCharset());
		byte[] buf = model.encode(x);
		int length = buf.length;
		if (256 > length) {
			model.append(new byte[] { XsonConst.CURRENCY, (byte) length });
		} else {
			throw new XsonSerializerException("long string in Currency : " + x);
		}
		model.append(buf);
	}
}