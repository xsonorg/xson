package org.xson.serializer;

import java.util.Date;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class DateUtilSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		Date date = (Date) target;
		model.append(XsonConst.DATE_UTIL);
		model.append(ByteUtils.longToByteWithMark(date.getTime()));
	}
}