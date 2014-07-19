package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;

public class EnumSerializer extends DefaultSerializer {

	public final static EnumSerializer instance = new EnumSerializer();
	
	@Override
	public void write(Object target, ByteModel model) {
		model.appendCreateUserObject(target.getClass());
		Enum<?> x = (Enum<?>) target;
		model.append((byte) x.ordinal());
	}

}