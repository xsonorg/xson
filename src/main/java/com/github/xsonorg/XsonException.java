package com.github.xsonorg;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonException extends RuntimeException {

	private static final long serialVersionUID = -7296834153811811698L;

	public XsonException(String message) {
		super(message);
	}

	public XsonException(Throwable cause) {
		super(cause);
	}

	public XsonException(String message, Throwable cause) {
		super(message, cause);
	}

}
