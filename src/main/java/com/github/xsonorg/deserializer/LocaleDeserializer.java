package com.github.xsonorg.deserializer;

import java.util.Locale;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.codecs.CharsetUtils;
import com.github.xsonorg.util.ByteUtils;

public class LocaleDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int length = ByteUtils.byteToInt(value[model.getIndex() + 1]);
		model.incrementIndex(2);

		Locale returnValue = null;
		// String str = new String(value, model.getIndex(), length,
		// model.getCharset());

		String str = model.decode(value, model.getIndex(), length,
				CharsetUtils.ASCII);

		String[] items = str.split("_");
		int itemsLength = items.length;
		if (itemsLength == 3) {
			returnValue = new Locale(items[0], items[1], items[2]);
		} else if (itemsLength == 2) {
			returnValue = new Locale(items[0], items[1]);
		} else if (itemsLength == 1) {
			returnValue = new Locale(items[0]);
		} else {
			throw new XsonDeserializerException("Illegal locale structures : "
					+ str);
		}
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}