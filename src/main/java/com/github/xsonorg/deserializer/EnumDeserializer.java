package com.github.xsonorg.deserializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.util.ByteUtils;

public class EnumDeserializer implements XsonReader {

	private static Map<Class<?>, Map<Integer, Enum<?>>> enumCacheMap = new HashMap<Class<?>, Map<Integer, Enum<?>>>();

	public static void cleanUpCache() {
		enumCacheMap.clear();
	}

	@Override
	public Object read(ReaderModel model) {
		Class<?> type = model.getUserType();
		int ordinal = ByteUtils.byteToInt(model.getValue()[model.getIndex()]);
		Map<Integer, Enum<?>> enumMap = enumCacheMap.get(type);
		if (null == enumMap) {
			enumMap = crateEnumMap(type);
			enumCacheMap.put(type, enumMap);
		}
		Enum<?> returnValue = enumMap.get(ordinal);
		if (null == returnValue) {
			throw new XsonDeserializerException("未知的枚举 " + type.getName()
					+ " at index " + ordinal);
		}
		model.incrementIndex(1);
		model.appendObject(returnValue);
		return returnValue;
	}

	private Map<Integer, Enum<?>> crateEnumMap(Class<?> type) {
		Map<Integer, Enum<?>> enumMap = new HashMap<Integer, Enum<?>>();
		try {
			Method valueMethod = type.getMethod("values");
			Object[] values = (Object[]) valueMethod.invoke(null);
			for (Object value : values) {
				Enum<?> e = (Enum<?>) value;
				enumMap.put(e.ordinal(), e);
			}
		} catch (Exception ex) {
			throw new XsonDeserializerException("init enum values error, "
					+ type.getName());
		}
		return enumMap;
	}

}