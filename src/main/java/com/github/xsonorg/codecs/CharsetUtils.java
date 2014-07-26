package com.github.xsonorg.codecs;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;

import com.github.xsonorg.XsonException;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class CharsetUtils {

	public final static Charset UTF8 = Charset.forName("UTF-8");

	public final static Charset ASCII = Charset.forName("US-ASCII");

	private static Map<String, Charset> charsetMap = new HashMap<String, Charset>();

	public static Charset lookup(final String name) {
		try {
			Charset charset = charsetMap.get(name);
			if (null != charset) {
				return charset;
			}
			charset = Charset.forName(name);
			charsetMap.put(name, charset);
			return charset;
		} catch (final UnsupportedCharsetException ex) {
			throw new XsonException(ex);
		}
	}

	// static {
	// lookup("US-ASCII");
	// lookup("ISO-8859-1");
	// lookup("UTF-8");
	// lookup("UTF-16BE");
	// lookup("UTF-16LE");
	// lookup("UTF-16");
	// }

}
