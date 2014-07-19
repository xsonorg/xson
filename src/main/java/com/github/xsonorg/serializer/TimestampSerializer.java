package com.github.xsonorg.serializer;

import java.sql.Timestamp;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

public class TimestampSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Timestamp timestamp = (Timestamp) target;
		model.append(XsonConst.TIMESTAMP_SQL);
		model.append(ByteUtils.longToByteWithMark(timestamp.getTime()));
	}
}