package org.xson.serializer;

import java.util.Locale;

import org.xson.ByteModel;
import org.xson.XsonConst;

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