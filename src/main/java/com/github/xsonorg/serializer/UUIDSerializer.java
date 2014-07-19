package com.github.xsonorg.serializer;

import java.util.UUID;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

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