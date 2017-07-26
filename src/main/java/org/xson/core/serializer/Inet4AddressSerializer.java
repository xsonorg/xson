package org.xson.core.serializer;

import java.net.Inet4Address;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class Inet4AddressSerializer extends DefaultSerializer {

	public final static Inet4AddressSerializer instance = new Inet4AddressSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		Inet4Address address = (Inet4Address) target;
		model.writeByte1(XsonConst.INET4ADDRESS);
		model.writeByteArray(address.getAddress(), true);
	}
}