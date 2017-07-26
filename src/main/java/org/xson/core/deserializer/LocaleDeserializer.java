package org.xson.core.deserializer;

import java.util.Locale;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class LocaleDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		String str = model.getString();
		Locale returnValue = null;
		String[] items = str.split("_");
		if (items.length == 3) {
			returnValue = new Locale(items[0], items[1], items[2]);
		} else if (items.length == 2) {
			returnValue = new Locale(items[0], items[1]);
		} else if (items.length == 1) {
			returnValue = new Locale(items[0]);
		} else {
			throw new XsonDeserializerException("Illegal locale structures : " + str);
		}
		model.appendObject(returnValue);
		return returnValue;
	}
}