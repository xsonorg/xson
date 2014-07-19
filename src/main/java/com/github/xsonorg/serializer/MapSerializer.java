package com.github.xsonorg.serializer;

import java.util.Map;

import com.github.xsonorg.ByteModel;

public class MapSerializer extends DefaultSerializer {
	
	public final static MapSerializer instance = new MapSerializer();

	@Override
	public void write(Object target, ByteModel model) {
		model.appendCreateUserObject(target.getClass());
		Map<?, ?> map = (Map<?, ?>) target;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			model.writeObject(entry.getKey());
			model.writeObject(entry.getValue());
		}
		model.appendEnd();
	}
}
