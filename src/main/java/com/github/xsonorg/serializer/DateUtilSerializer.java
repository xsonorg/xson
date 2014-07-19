package com.github.xsonorg.serializer;

import java.util.Date;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

public class DateUtilSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Date date = (Date) target;
		model.append(XsonConst.DATE_UTIL);
		model.append(ByteUtils.longToByteWithMark(date.getTime()));
	}
}