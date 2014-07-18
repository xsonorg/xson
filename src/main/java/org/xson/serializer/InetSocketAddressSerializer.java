package org.xson.serializer;

import java.net.InetSocketAddress;

import org.xson.ByteModel;
import org.xson.XsonConst;
import org.xson.util.ByteUtils;

public class InetSocketAddressSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, ByteModel model) {
		InetSocketAddress address = (InetSocketAddress) target;
		model.append(XsonConst.INETSOCKETADDRESS);
		InetAddressSerializer.instance.write(address.getAddress(), model);
		// port only support 65535
		model.append(ByteUtils.shortTo2Byte((short) address.getPort()));
	}
}