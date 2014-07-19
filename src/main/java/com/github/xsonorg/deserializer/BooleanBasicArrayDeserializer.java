package com.github.xsonorg.deserializer;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.XsonReader;
import com.github.xsonorg.util.ByteUtils;

public class BooleanBasicArrayDeserializer implements XsonReader {
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
		case XsonConst.CONTROL_CREATE_SYS_ARRAY3:// 3byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 3);
			model.incrementIndex(3);
			break;
		default:// 4byte
			length = ByteUtils.byteToInt(value, model.getIndex(), 4);
			model.incrementIndex(4);
		}
		boolean[] array = new boolean[length];
		int pos = model.getIndex();
		for (int i = 0; i < array.length; i++) {
			array[i] = (value[pos + i] == XsonConst.TRUE) ? true : false;
		}
		model.appendObject(array);
		model.incrementIndex(length);
		return array;
	}
}