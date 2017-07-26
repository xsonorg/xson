package org.xson.core.serializer;

import java.sql.Timestamp;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class TimestampSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Timestamp timestamp = (Timestamp) target;
		model.writeByte1(XsonConst.TIMESTAMP_SQL);
		model.writeLong(timestamp.getTime());
	}
}