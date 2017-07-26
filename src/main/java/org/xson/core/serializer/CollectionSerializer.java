package org.xson.core.serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.xson.core.WriterModel;

public class CollectionSerializer extends DefaultSerializer {

	public final static CollectionSerializer instance = new CollectionSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		model.appendCreateUserObject(target.getClass());
		if (target instanceof ArrayList) {
			arrayListWrite(target, model);
		} else {
			collectionWrite(target, model);
		}
		model.writeEnd();
	}

	private void arrayListWrite(Object target, WriterModel model) {
		ArrayList<?> list = (ArrayList<?>) target;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			model.writeObject(list.get(i));
		}
	}

	private void collectionWrite(Object target, WriterModel model) {
		Iterator<?> iterator = ((Collection<?>) target).iterator();
		while (iterator.hasNext()) {
			model.writeObject(iterator.next());
		}
	}
}
