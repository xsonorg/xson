package com.github.xsonorg.deserializer;

import java.lang.reflect.Array;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.XsonSupport;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.XsonTypeUtils;

public class ObjectArrayDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int index = model.getIndex();

		byte frameType = value[index];
		int componentIndex = ByteUtils.byteToInt(value[index + 1]);
		int dimension = ByteUtils.byteToInt(value[index + 2]);
		Class<?> componentType = null;

		model.incrementIndex(3);

		int length = 0;
		switch (frameType) {
		case XsonConst.CONTROL_CREATE_SYS_ARRAY1:// 1byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 1);
			model.incrementIndex(1);
			componentType = XsonSupport.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_USER_ARRAY1:// 1byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 1);
			model.incrementIndex(1);
			componentType = model.getUserType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_SYS_ARRAY2:// 2byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 2);
			model.incrementIndex(2);
			componentType = XsonSupport.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_SYS_ARRAY3:// 3byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 3);
			model.incrementIndex(3);
			componentType = XsonSupport.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_SYS_ARRAY:// 4byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 4);
			model.incrementIndex(4);
			componentType = XsonSupport.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_USER_ARRAY:// 4byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 4);
			model.incrementIndex(4);
			componentType = model.getUserType(componentIndex);
			break;
		}

		componentType = XsonTypeUtils.getArrayType(componentType, dimension);
		Object[] array = (Object[]) Array.newInstance(componentType, length);

		model.appendObject(array);
		for (int i = 0; i < length; i++) {
			array[i] = model.getObject();
		}
		return array;
	}

}
