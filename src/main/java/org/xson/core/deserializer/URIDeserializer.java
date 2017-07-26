package org.xson.core.deserializer;

import java.net.URI;
import java.net.URISyntaxException;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class URIDeserializer implements XsonReader {
	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);
		String str = model.getString();
		URI returnValue = null;
		try {
			returnValue = new URI(str);
		} catch (URISyntaxException e) {
			throw new XsonDeserializerException("create URI error: " + str, e);
		}
		model.appendObject(returnValue);
		return returnValue;
	}
}