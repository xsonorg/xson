package org.xson.core.serializer;

import java.net.Inet4Address;
import java.net.Inet6Address;

import org.xson.core.WriterModel;

public class InetAddressSerializer extends DefaultSerializer {

	public final static InetAddressSerializer instance = new InetAddressSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		if (target instanceof Inet4Address) {
			Inet4AddressSerializer.instance.write(target, model);
		} else if (target instanceof Inet6Address) {
			Inet6AddressSerializer.instance.write(target, model);
		} else {
			throw new XsonSerializerException("Unsupported InetAddress Class : " + target.getClass());
		}
	}
}