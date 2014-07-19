package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ArraySerializer extends DefaultSerializer {

	public final static ArraySerializer instance = new ArraySerializer();

	@Override
	public void write(Object target, ByteModel model) {
		Class<?> targetClass = target.getClass();
		int dimensions = XsonTypeUtils.getArrayDimensions(targetClass);
		Class<?> componentType = XsonTypeUtils.getComponentType(targetClass);
		Object[] array = (Object[]) target;
		int length = array.length;
		model.appendCreateArray(componentType, dimensions, length);
		for (int i = 0; i < length; i++) {
			model.writeObject(array[i]);
		}
	}

}
