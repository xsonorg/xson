package org.xson.serializer;

import java.sql.Timestamp;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class TimestampSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Timestamp timestamp = (Timestamp) target;
		model.append(XsonConst.TIMESTAMP_SQL);
		model.append(ByteUtils.longToByteWithMark(timestamp.getTime()));
	}
}