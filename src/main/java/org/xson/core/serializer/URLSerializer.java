package org.xson.core.serializer;

import java.net.URL;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class URLSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		URL uri = (URL) target;
		String x = uri.toString();
		model.writeByte1(XsonConst.URL_WRAP);
		model.writeString(x);
	}
}