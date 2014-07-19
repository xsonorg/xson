package com.github.xsonorg.serializer;

import java.net.Inet4Address;

import com.github.xsonorg.ByteModel;
import com.github.xsonorg.XsonConst;

public class Inet4AddressSerializer extends DefaultSerializer {

	public final static Inet4AddressSerializer instance = new Inet4AddressSerializer();

	@Override
	public void write(Object target, ByteModel model) {
		Inet4Address address = (Inet4Address) target;
		model.append(XsonConst.INET4ADDRESS);
		model.append(address.getAddress());
	}
}