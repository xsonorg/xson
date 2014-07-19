package com.github.xsonorg.deserializer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.github.xsonorg.ReaderModel;
import com.github.xsonorg.XsonReader;

public class Inet6AddressDeserializer implements XsonReader {
	
	public final static Inet6AddressDeserializer instance = new Inet6AddressDeserializer();
	
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);// -->skip type
		int length = 16;
		byte[] addr = new byte[length];
		System.arraycopy(model.getValue(), model.getIndex(), addr, 0, length);
		InetAddress returnValue = null;
		try {
			returnValue = InetAddress.getByAddress(addr);
		} catch (UnknownHostException e) {
			throw new XsonDeserializerException(e);
		}
		model.appendObject(returnValue);
		model.incrementIndex(length);
		return returnValue;
	}
}