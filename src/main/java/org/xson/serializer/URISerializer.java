package org.xson.serializer;

import java.net.URI;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class URISerializer extends DefaultSerializer {

	private byte[] l2Mark = { XsonConst.URI_WRAP, XsonConst.STRING2 };

	private byte[] l3Mark = { XsonConst.URI_WRAP, XsonConst.STRING3 };

	private byte[] l4bMark = { XsonConst.URI_WRAP, XsonConst.STRING };

	@Override
	public void write(Object target, ByteModel model) {
		URI uri = (URI) target;
		String x = uri.toString();
		byte[] buf = x.getBytes(model.getCharset());
		int length = buf.length;
		if (256 > length) {// 2^8
			model.append(new byte[] { XsonConst.URI_WRAP, XsonConst.STRING1,
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