package com.github.xsonorg.serializer;

import java.net.Inet6Address;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;

public class Inet6AddressSerializer extends DefaultSerializer {

	public final static Inet6AddressSerializer instance = new Inet6AddressSerializer();

	@Override
	public void write(Object target, ByteModel model) {
		Inet6Address address = (Inet6Address) target;
		model.append(XsonConst.INET6ADDRESS);
		model.append(address.getAddress());
	}
}