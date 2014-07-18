package org.xson.deserializer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.xson.ReaderModel;
import org.xson.XsonConst;
import org.xson.XsonReader;
import org.xson.util.ByteUtils;

public class InetSocketAddressDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);// -->skip type
		InetAddress address = null;
		byte frameType = model.getValue()[model.getIndex()];
		if (XsonConst.INET4ADDRESS == frameType) {
			model.incrementIndex(1);// -->skip type
			byte[] value = model.getValue();
			int index = model.getIndex();
			byte[] addr = { value[index], value[index + 1], value[index + 2],
					value[index + 3] };
			try {
				address = InetAddress.getByAddress(addr);
			} catch (UnknownHostException e) {
				throw new XsonDeserializerException(e);
			}
			model.incrementIndex(4);
		} else if (XsonConst.INET6ADDRESS == frameType) {
			model.incrementIndex(1);// -->skip type
			int length = 16;
			byte[] addr = new byte[length];
			System.arraycopy(model.getValue(), model.getIndex(), addr, 0,
					length);
			try {
				address = InetAddress.getByAddress(addr);
			} catch (UnknownHostException e) {
				throw new XsonDeserializerException(e);
			}
			model.incrementIndex(length);
		} else {
			throw new XsonDeserializerException("Illegal InetAddress mark '"
					+ Integer.toHexString(frameType & 0xFF) + "' at index "
					+ model.getIndex());
		}
		// int port = model.getInt();
		int port = ByteUtils.byteToInt(model.getValue(), model.getIndex(), 2);
		InetSocketAddress returnValue = new InetSocketAddress(address, port);
		model.appendObject(returnValue);
		model.incrementIndex(2);
		return returnValue;
	}

}