package com.github.xsonorg.serializer;

import java.net.URL;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

public class URLSerializer extends DefaultSerializer {

	private byte[] l2Mark = { XsonConst.URL_WRAP, XsonConst.STRING2 };

	private byte[] l3Mark = { XsonConst.URL_WRAP, XsonConst.STRING3 };

	private byte[] l4bMark = { XsonConst.URL_WRAP, XsonConst.STRING };

	@Override
	public void write(Object target, ByteModel model) {
		URL uri = (URL) target;
		String x = uri.toString();
		// byte[] buf = x.getBytes(model.getCharset());
		byte[] buf = model.encode(x);
		int length = buf.length;
		if (256 > length) {// 2^8
			model.append(new byte[] { XsonConst.URL_WRAP, XsonConst.STRING1,
					(byte) length });
		} else if (65536 > length) {// 2^16
			model.append(l2Mark);
			model.append(ByteUtils.intToByte(length));
		} else if (16777216 > length) {// 2^24
			model.append(l3Mark);
			model.append(ByteUtils.intToByte(length));
		} else {// 2^32
			model.append(l4bMark);
			model.append(ByteUtils.intToByte(length));
		}
		model.append(buf);
	}
}