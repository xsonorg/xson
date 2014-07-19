package com.github.xsonorg.serializer;

import java.util.Locale;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;

public class LocaleSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Locale locale = (Locale) target;
		String x = locale.toString();
		byte[] buf = x.getBytes(model.getCharset());
		int length = buf.length;
		if (256 > length) {
			model.append(new byte[] { XsonConst.LOCALE, (byte) length });
		} else {
			throw new XsonSerializerException("long string in locale : " + x);
		}
		model.append(buf);
	}
}