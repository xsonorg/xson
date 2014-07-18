package org.xson.serializer;

import org.xson.ByteModel;

public class EnumSerializer extends DefaultSerializer {

	public final static EnumSerializer instance = new EnumSerializer();
	
	@Override
	public void write(Object target, ByteModel model) {
		model.appendCreateUserObject(target.getClass());
		Enum<?> x = (Enum<?>) target;
		model.append((byte) x.ordinal());
	}

}