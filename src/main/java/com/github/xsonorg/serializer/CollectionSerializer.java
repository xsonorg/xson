package com.github.xsonorg.serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.github.xsonorg.ByteModel;

public class CollectionSerializer extends DefaultSerializer {

	public final static CollectionSerializer instance = new CollectionSerializer();

	@Override
	public void write(Object target, ByteModel model) {
		model.appendCreateUserObject(target.getClass());
		if (target instanceof ArrayList) {
			arrayListWrite(target, model);
		} else {
			collectionWrite(target, model);
		}
		model.appendEnd();
	}

	private void arrayListWrite(Object target, ByteModel model) {
		ArrayList<?> list = (ArrayList<?>) target;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			model.writeObject(list.get(i));
		}
	}

	private void collectionWrite(Object target, ByteModel model) {
		Iterator<?> iterator = ((Collection<?>) target).iterator();
		while (iterator.hasNext()) {
			model.writeObject(iterator.next());
		}
	}
}
