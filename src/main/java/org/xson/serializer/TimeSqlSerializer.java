package org.xson.serializer;

import java.sql.Time;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class TimeSqlSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Time time = (Time) target;
		model.append(XsonConst.TIME_SQL);
		model.append(ByteUtils.longToByteWithMark(time.getTime()));
	}
}