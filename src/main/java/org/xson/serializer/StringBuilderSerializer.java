package org.xson.serializer;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class StringBuilderSerializer extends DefaultSerializer {

	private byte[] l2Mark = { XsonConst.STRING_BUILDER, XsonConst.STRING2 };

	private byte[] l3Mark = { XsonConst.STRING_BUILDER, XsonConst.STRING3 };

	private byte[] l4Mark = { XsonConst.STRING_BUILDER, XsonConst.STRING };

	@Override
	public void write(Object target, ByteModel model) {
		StringBuilder sb = (StringBuilder) target;
		String x = sb.toString();
		if (0 == sb.length()) {
			model.append(new byte[] { XsonConst.STRING_BUILDER, XsonConst.NULL });
		} else {
			byte[] buf = x.getBytes(model.getCharset());
			int length = buf.length;
			if (256 > length) {// 2^8
				model.append(new byte[] { XsonConst.STRING_BUILDER,
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