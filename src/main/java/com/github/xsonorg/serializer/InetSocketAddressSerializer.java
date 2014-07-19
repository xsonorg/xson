package com.github.xsonorg.serializer;

import java.net.InetSocketAddress;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;
import com.github.xsonorg.util.ByteUtils;

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