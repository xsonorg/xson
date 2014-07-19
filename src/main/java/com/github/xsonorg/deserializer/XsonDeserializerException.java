package com.github.xsonorg.deserializer;

import com.github.xsonorg.XsonException;

public class XsonDeserializerException extends XsonException {

	private static final long serialVersionUID = 5197823482208575204L;

	public XsonDeserializerException(String message) {
		super(message);
	}

	public XsonDeserializerException(Throwable cause) {
		super(cause);
	}

	public XsonDeserializerException(String message, Throwable cause) {
		super(message, cause);
	}

}
