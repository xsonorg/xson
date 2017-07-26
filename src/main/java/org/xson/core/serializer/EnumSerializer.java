package org.xson.core.serializer;

import org.xson.core.WriterModel;

public class EnumSerializer extends DefaultSerializer {

	public final static EnumSerializer instance = new EnumSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		model.appendCreateUserObject(target.getClass());
		Enum<?> x = (Enum<?>) target;
		model.writeInt(x.ordinal());
	}
}