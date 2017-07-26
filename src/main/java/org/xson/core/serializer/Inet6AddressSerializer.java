package org.xson.core.serializer;

import java.net.Inet6Address;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

public class Inet6AddressSerializer extends DefaultSerializer {

	public final static Inet6AddressSerializer instance = new Inet6AddressSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		Inet6Address address = (Inet6Address) target;
		model.writeByte1(XsonConst.INET6ADDRESS);
		model.writeByteArray(address.getAddress(), true);
	}
}