package org.xson.core.deserializer;

import java.net.MalformedURLException;
import java.net.URL;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class URLDeserializer implements XsonReader {

	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		String str = model.getString();
		URL returnValue = null;
		try {
			returnValue = new URL(str);
		} catch (MalformedURLException e) {
			throw new XsonDeserializerException("create URL error: " + str, e);
		}
		model.appendObject(returnValue);
		return returnValue;
	}

}