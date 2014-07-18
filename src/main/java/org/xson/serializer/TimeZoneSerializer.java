package org.xson.serializer;

import java.util.TimeZone;

import org.xson.ByteModel;
import org.xson.XsonConst;

public class TimeZoneSerializer extends DefaultSerializer {

	public final static TimeZoneSerializer instance = new TimeZoneSerializer();
	
	@Override
	public void write(Object target, ByteModel model) {
		TimeZone timeZone = (TimeZone) target;
		String x = timeZone.getID();
		byte[] buf = x.getBytes(model.getCharset());
		int length = buf.length;
		if (256 > length) {
			model.append(new byte[] { XsonConst.TIMEZONE, (byte) length });
		} else {
			throw new XsonSerializerException("long string in TimeZone : " + x);
		}
		model.append(buf);
	}
}