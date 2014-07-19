package com.github.xsonorg.serializer;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

public class StringBufferSerializer extends DefaultSerializer {

	private byte[] l2Mark = { XsonConst.STRING_BUFFER, XsonConst.STRING2 };

	private byte[] l3Mark = { XsonConst.STRING_BUFFER, XsonConst.STRING3 };

	private byte[] l4Mark = { XsonConst.STRING_BUFFER, XsonConst.STRING };

	@Override
	public void write(Object target, ByteModel model) {
		StringBuffer sb = (StringBuffer) target;
		String x = sb.toString();
		if (0 == sb.length()) {
			model.append(new byte[] { XsonConst.STRING_BUFFER, XsonConst.NULL });
		} else {
			byte[] buf = x.getBytes(model.getCharset());
			int length = buf.length;
			if (256 > length) {// 2^8
				model.append(new byte[] { XsonConst.STRING_BUFFER,
						XsonConst.STRING1, (byte) length });
			} else if (65536 > length) {// 2^16
				model.append(l2Mark);
				model.append(ByteUtils.intToByte(length));
			} else if (16777216 > length) {// 2^24
				model.append(l3Mark);
				model.append(ByteUtils.intToByte(length));
			} else {// 2^32
				model.append(l4Mark);
				model.append(ByteUtils.intToByte(length));
			}
			model.append(buf);
		}
	}
}
