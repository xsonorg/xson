package org.xson.deserializer;

import org.xson.ReaderModel;
import org.xson.XsonConst;
import org.xson.XsonReader;
import org.xson.util.ByteUtils;

public class ByteBasicArrayDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		byte[] value = model.getValue();
		byte frameType = value[model.getIndex()];
		model.incrementIndex(3);// skip 控制类型,数组类型,数组维度
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
		byte[] array = new byte[length];
		System.arraycopy(value, model.getIndex(), array, 0, length);
		model.appendObject(array);
		model.incrementIndex(length);
		return array;
	}
}