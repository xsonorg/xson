package org.xson.serializer;

import java.util.UUID;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class UUIDSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		UUID x = (UUID) target;
		long mostSigBits = x.getMostSignificantBits();
		long leastSigBits = x.getLeastSignificantBits();
		model.append(XsonConst.UUID_WRAP);
		model.append(ByteUtils.longToByteWithMark(mostSigBits));
		model.append(ByteUtils.longToByteWithMark(leastSigBits));
	}

}