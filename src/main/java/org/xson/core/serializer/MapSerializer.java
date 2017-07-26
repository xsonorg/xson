package org.xson.core.serializer;

import java.util.Map;

import org.xson.core.WriterModel;

public class MapSerializer extends DefaultSerializer {

	public final static MapSerializer instance = new MapSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		model.appendCreateUserObject(target.getClass());
		Map<?, ?> map = (Map<?, ?>) target;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			model.writeObject(entry.getKey());
			model.writeObject(entry.getValue());
		}
		model.writeEnd();
	}
}
