package com.github.xsonorg.codecs;

public class AsciiCodecs {

	private final static byte[] EMPTY_STRING = {};

	public static byte[] encode(String val) {
		int offset = 0;
		int length = val.length();

		// empty string
		if (0 == length) {
			return EMPTY_STRING;
		}

		byte[] buffer = new byte[length];
		for (int i = 0; i < length; i++) {
			buffer[offset++] = (byte) (val.charAt(i));
		}

		return buffer;
	}

	public static String decode(byte[] buffer, int offset, int length) {
		// if (0 == length || offset == length) {
		// return new String();
		// }
		// fix bug
		if (0 == length) {
			return new String();
		}
		char[] chars = new char[length];
		int charIndex = 0;
		int end = offset + length;
		while (offset < end) {
			// int b = buffer[offset++] & 0xFF;
			// chars[charIndex++] = (char) b;
			chars[charIndex++] = (char) buffer[offset++];
		}
		return new String(chars, 0, charIndex);
	}
}
