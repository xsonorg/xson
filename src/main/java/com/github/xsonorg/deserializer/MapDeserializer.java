package com.github.xsonorg.deserializer;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class MapDeserializer implements XsonReader {

	@Override
	public Object read(ReaderModel model) {
		Class<?> type = model.getUserType();
		Map<Object, Object> returnValue = createMap(type);
		model.appendObject(returnValue);
		readMap(model, returnValue);
		model.endObject();
		return returnValue;
	}

	private Map<Object, Object> createMap(Class<?> type) {
		// 如果有有私有Class或者空构造函数,则会出现问题
		Map<Object, Object> map = null;
		if (type == HashMap.class) {
			map = new HashMap<Object, Object>();
		} else if (type == Hashtable.class) {
			map = new Hashtable<Object, Object>();
		} else if (type == TreeMap.class) {
			map = new TreeMap<Object, Object>();
		} else if (type == LinkedHashMap.class) {
			map = new LinkedHashMap<Object, Object>();
		} else if (type == ConcurrentHashMap.class) {
			map = new ConcurrentHashMap<Object, Object>();
		} else if (type == IdentityHashMap.class) {
			map = new IdentityHashMap<Object, Object>();
		} else if (type == Properties.class) {
			map = new Properties();
		} else if (type == EnumMap.class) {
			//
		} else {
			try {
				// map = (Map<Object, Object>) type.newInstance();
				map = (Map) type.newInstance();
			} catch (Exception e) {
				throw new XsonDeserializerException(
						"create map instane error, class " + type.getName());
			}
		}
		return map;
	}

	private void readMap(ReaderModel model, Map<Object, Object> map) {
		while (!model.isEnd() && model.isBound()) {
			Object key = model.getObject();
			Object value = model.getObject();
			map.put(key, value);
		}
	}
}