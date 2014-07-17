package org.xson.deserializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import org.xson.ReaderModel;
import org.xson.XsonReader;

public class CollectionDeserializer implements XsonReader {

	@Override
	public Object read(ReaderModel model) {
		Class<?> type = model.getUserType();
		Collection<Object> returnValue = createCollection(type);
		model.appendObject(returnValue);
		while (!model.isEnd() && model.isBound()) {
			returnValue.add(model.getObject());
		}
		model.endObject();
		return returnValue;
	}

	private Collection<Object> createCollection(Class<?> type) {
		// 如果有有私有Class或者空构造函数,则会出现问题
		Collection collection = null;
		if (type == ArrayList.class) {
			collection = new ArrayList<Object>();
		} else if (type == LinkedList.class) {
			collection = new LinkedList<Object>();
		} else if (type == Vector.class) {
			collection = new Vector<Object>();
		} else if (type == Stack.class) {
			collection = new Stack<Object>();
		} else if (type == HashSet.class) {
			collection = new HashSet<Object>();
		} else if (type == TreeSet.class) {
			collection = new TreeSet<Object>();
		} else if (type == LinkedHashSet.class) {
			collection = new LinkedHashSet<Object>();
		} else if (type.isAssignableFrom(EnumSet.class)) {
			// list = EnumSet.noneOf((Class<Enum>)itemType);
		} else {
			try {
				collection = (Collection) type.newInstance();
			} catch (Exception e) {
				throw new XsonDeserializerException(
						"create collection instane error, class "
								+ type.getName());
			}
		}
		return collection;
	}

}
