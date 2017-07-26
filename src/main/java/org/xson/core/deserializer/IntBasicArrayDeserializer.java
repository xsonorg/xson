package org.xson.core.deserializer;

import org.xson.core.ReaderModel;
import org.xson.core.XsonConst;
import org.xson.core.XsonReader;
import org.xson.core.util.ByteUtils;

public class IntBasicArrayDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		byte frameType = value[model.getIndex()];
		model.incrementIndex(3);
		int length = 0;
		switch (frameType) {
		case XsonConst.CONTROL_CREATE_SYS_ARRAY1:// 1byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 1);
			model.incrementIndex(1);
			break;
		case XsonConst.CONTROL_CREATE_SYS_ARRAY2:// 2byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 2);
			model.incrementIndex(2);
			break;
		default:// 4byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 4);
			model.incrementIndex(4);
		}
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = model.getInt();
		}
		model.appendObject(array);
		return array;
	}
}
