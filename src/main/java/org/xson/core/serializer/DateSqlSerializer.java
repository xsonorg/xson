package org.xson.core.serializer;

import java.sql.Date;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class DateSqlSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Date date = (Date) target;
		model.writeByte1(XsonConst.DATE_SQL);
		model.writeLong(date.getTime());
	}
}