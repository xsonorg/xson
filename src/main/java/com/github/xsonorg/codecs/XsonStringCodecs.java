package com.github.xsonorg.codecs;

import java.nio.charset.Charset;

public class XsonStringCodecs {

	protected final static byte[] EMPTY_STRING = {};
	
	public static byte[] encode(Charset charset, String val) {
		if (CharsetUtils.UTF8 == charset) {
			return Utf8Codecs.encode(val);
		} else if (CharsetUtils.ASCII == charset) {
			return AsciiCodecs.encode(val);
		}
		return val.getBytes(charset);
	}

	public static String decode(Charset charset, byte[] buf, int offset,
			int length) {
		if (CharsetUtils.UTF8 == charset) {
			return Utf8Codecs.decode(buf, offset, length);
		} else if (CharsetUtils.ASCII == charset) {
			return AsciiCodecs.decode(buf, offset, length);
		}
		return new String(buf, offset, length, charset);
	}
}
