package org.xson.core.serializer;

import org.xson.core.WriterModel;

public class StringSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		String x = (String) target;
		model.writeString(x);
	}

}
