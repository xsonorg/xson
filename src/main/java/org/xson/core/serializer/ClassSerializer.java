package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class ClassSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Class<?> clazz = (Class<?>) target;
		model.writeByte1(XsonConst.CLASS);
		model.writeString(clazz.getName());
	}

}
