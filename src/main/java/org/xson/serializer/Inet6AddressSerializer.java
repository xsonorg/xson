package org.xson.serializer;

import java.net.Inet6Address;

import org.xson.ByteModel;
import org.xson.XsonConst;

public class Inet6AddressSerializer extends DefaultSerializer {

	public final static Inet6AddressSerializer instance = new Inet6AddressSerializer();

	@Override
	public void write(Object target, ByteModel model) {
		Inet6Address address = (Inet6Address) target;
		model.append(XsonConst.INET6ADDRESS);
		model.append(address.getAddress());
	}
}