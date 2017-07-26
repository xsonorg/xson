package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Byte x = (Byte) target;
		// byte[] buf = XsonSupport.getByteDateFrame(x.byteValue());
		// buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		// model.append(buf);
		// model.writeByte1((byte) (XsonConst.BYTE | XsonConst.BASIC_MASK));
		// model.writeByte1(x.byteValue());
		model.writeByte(x.byteValue(), XsonConst.BASIC_MASK);
	}

}
