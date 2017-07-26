package org.xson.core.deserializer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class Inet4AddressDeserializer implements XsonReader {
	
	public final static Inet4AddressDeserializer instance = new Inet4AddressDeserializer();
	
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);// -->skip type
		byte[] value = model.getValue();
		int index = model.getIndex();
		byte[] addr = {value[index], value[index + 1], value[index + 2], value[index + 3]};
		InetAddress returnValue = null;
		try {
			returnValue = InetAddress.getByAddress(addr);
		} catch (UnknownHostException e) {
			throw new XsonDeserializerException(e);
		}
		model.appendObject(returnValue);
		model.incrementIndex(4);
		return returnValue;
	}
}