package org.xson.core.serializer;

import java.net.InetSocketAddress;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class InetSocketAddressSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		InetSocketAddress address = (InetSocketAddress) target;
		model.writeByte1(XsonConst.INETSOCKETADDRESS);
		model.writeObject(address.getAddress());
		model.writeShort2((short) address.getPort());
	}
}