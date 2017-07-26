package org.xson.core.serializer;

import java.sql.Time;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class TimeSqlSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Time time = (Time) target;
		model.writeByte1(XsonConst.TIME_SQL);
		model.writeLong(time.getTime());
	}
}