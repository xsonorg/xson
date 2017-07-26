package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class StringBufferSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		StringBuffer sb = (StringBuffer) target;
		String x = sb.toString();
		model.writeByte1(XsonConst.STRING_BUFFER);
		model.writeString(x);
	}
}
