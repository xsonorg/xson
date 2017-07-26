package org.xson.core.serializer;

import java.net.URI;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class URISerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		URI uri = (URI) target;
		String x = uri.toString();
		model.writeByte1(XsonConst.URI_WRAP);
		model.writeString(x);
	}
}