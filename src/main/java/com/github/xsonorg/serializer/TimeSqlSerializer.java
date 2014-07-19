package com.github.xsonorg.serializer;

import java.sql.Time;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

public class TimeSqlSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Time time = (Time) target;
		model.append(XsonConst.TIME_SQL);
		model.append(ByteUtils.longToByteWithMark(time.getTime()));
	}
}