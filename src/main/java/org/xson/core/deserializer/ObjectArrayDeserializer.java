package org.xson.core.deserializer;

import java.lang.reflect.Array;

import org.xson.core.ReaderModel;
import org.xson.core.XsonConst;
import org.xson.core.XsonReader;
import org.xson.core.util.ByteUtils;
import org.xson.core.util.XsonTypeUtils;

public class ObjectArrayDeserializer implements XsonReader {

	public final static ObjectArrayDeserializer instance = new ObjectArrayDeserializer();

	private ObjectArrayDeserializer() {
	}

	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		int index = model.getIndex();

		byte frameType = value[index];
		Class<?> componentType = null;

		int componentIndex = 0;
		int dimension = 0;
		int length = 0;

		switch (frameType) {
		case XsonConst.CONTROL_CREATE_SYS_ARRAY1:// 1byte
			componentIndex = ByteUtils.byteToInt(value[index + 1]);
			dimension = ByteUtils.byteToInt(value[index + 2]);
			length = ByteUtils.byteToInt(value, index + 3, 1);
			model.incrementIndex(4);
			componentType = model.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_SYS_ARRAY2:// 2byte
			componentIndex = ByteUtils.byteToInt(value[index + 1]);
			dimension = ByteUtils.byteToInt(value[index + 2]);
			length = ByteUtils.byteToInt(value, index + 3, 2);
			model.incrementIndex(5);
			componentType = model.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_SYS_ARRAY:// 4byte
			componentIndex = ByteUtils.byteToInt(value[index + 1]);
			dimension = ByteUtils.byteToInt(value[index + 2]);
			length = ByteUtils.byteToInt(value, index + 3, 4);
			model.incrementIndex(7);
			componentType = model.getSystemType(componentIndex);
			break;
		case XsonConst.CONTROL_CREATE_USER_ARRAY1:// 1byte
			model.incrementIndex(1);// -->type index
			componentIndex = model.getInt();

			index = model.getIndex();
			dimension = ByteUtils.byteToInt(value[index]);
			length = ByteUtils.byteToInt(value, index + 1, 1);
			model.incrementIndex(2);
			componentType = model.getUserType(componentIndex);

			break;
		case XsonConst.CONTROL_CREATE_USER_ARRAY:// 4byte
			model.incrementIndex(1);// -->type index
			componentIndex = model.getInt();

			index = model.getIndex();
			dimension = ByteUtils.byteToInt(value[index]);
			length = ByteUtils.byteToInt(value, index + 1, 4);
			model.incrementIndex(5);
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
