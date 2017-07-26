package org.xson.core.serializer;

import java.util.TimeZone;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class TimeZoneSerializer extends DefaultSerializer {

	public final static TimeZoneSerializer instance = new TimeZoneSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		TimeZone timeZone = (TimeZone) target;
		String x = timeZone.getID();
		model.writeByte1(XsonConst.TIMEZONE);
		model.writeString(x);
	}
}