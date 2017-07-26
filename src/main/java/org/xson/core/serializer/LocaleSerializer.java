package org.xson.core.serializer;

import java.util.Locale;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class LocaleSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Locale locale = (Locale) target;
		String x = locale.toString();
		model.writeByte1(XsonConst.LOCALE);
		model.writeString(x);
	}
}