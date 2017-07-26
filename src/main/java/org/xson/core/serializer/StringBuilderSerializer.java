package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class StringBuilderSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		StringBuilder sb = (StringBuilder) target;
		String x = sb.toString();
		model.writeByte1(XsonConst.STRING_BUILDER);
		model.writeString(x);
	}
}