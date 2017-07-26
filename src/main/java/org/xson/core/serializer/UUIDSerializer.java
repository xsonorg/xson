package org.xson.core.serializer;

import java.util.UUID;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class UUIDSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		UUID x = (UUID) target;
		long mostSigBits = x.getMostSignificantBits();
		long leastSigBits = x.getLeastSignificantBits();
		model.writeByte1(XsonConst.UUID_WRAP);
		model.writeLong(mostSigBits);
		model.writeLong(leastSigBits);
	}

}