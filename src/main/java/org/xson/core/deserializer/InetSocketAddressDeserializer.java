package org.xson.core.deserializer;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.xson.core.ReaderModel;
import org.xson.core.XsonConst;
import org.xson.core.XsonReader;
import org.xson.core.util.ByteUtils;

public class InetSocketAddressDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);// -->skip type
		InetAddress address = null;
		byte frameType = model.getValue()[model.getIndex()];
		if (XsonConst.INET4ADDRESS == frameType) {
			address = (InetAddress) model.getObject();
		} else if (XsonConst.INET6ADDRESS == frameType) {
			address = (InetAddress) model.getObject();
		} else {
			throw new XsonDeserializerException(
					"Illegal InetAddress mark '" + Integer.toHexString(frameType & 0xFF) + "' at index " + model.getIndex());
		}
		int port = ByteUtils.byteToInt(model.getValue(), model.getIndex(), 2);
		InetSocketAddress returnValue = new InetSocketAddress(address, port);
		model.appendObject(returnValue);
		model.incrementIndex(2);
		return returnValue;
	}

}