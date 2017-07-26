package org.xson.core.serializer;

import java.util.Date;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class DateUtilSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Date date = (Date) target;
		model.writeByte1(XsonConst.DATE_UTIL);
		model.writeLong(date.getTime());
	}
}