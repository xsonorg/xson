package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

public class StringSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		String x = (String) target;
		byte[] buf = x.getBytes(model.getCharset());
		int length = buf.length;
		if (256 > length) {// 2^8
			model.append(new byte[] { XsonConst.STRING1, (byte) length });
		} else if (65536 > length) {// 2^16
			byte[] head = ByteUtils.intToByteWithMark(length);
			head[0] = XsonConst.STRING2;
			model.append(head);
		} else if (16777216 > length) {// 2^24
			byte[] head = ByteUtils.intToByteWithMark(length);
			head[0] = XsonConst.STRING3;
			model.append(head);
		} else {// 2^32
			byte[] head = ByteUtils.intToByteWithMark(length);
			head[0] = XsonConst.STRING;
			model.append(head);
		}
		model.append(buf);
	}

}
