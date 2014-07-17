package org.xson.serializer;

import java.sql.Date;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class DateSqlSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Date date = (Date) target;
		model.append(XsonConst.DATE_SQL);
		model.append(ByteUtils.longToByteWithMark(date.getTime()));
	}
}