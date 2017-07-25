package com.github.xsonorg.codecs;

public class Utf8Codecs {

	private final static byte[] EMPTY_STRING = {};

	public static byte[] encode(String val) {
		int offset = 0;
		int length = val.length();

		// empty string
		if (0 == length) {
			return EMPTY_STRING;
		}

		int arrayLength = length * 3;
		byte[] buffer = new byte[arrayLength];

		// int arrayLength = length * 3;
		// byte[] buffer = BufferCache.getBytes(arrayLength);
		// arrayLength = buffer.length;

		for (int i = 0; i < length; i++) {
			char ch = val.charAt(i);
			if (ch < 0x80)
				buffer[offset++] = (byte) (ch);
			else if (ch < 0x800) {
				buffer[offset++] = (byte) (0xc0 + ((ch >> 6) & 0x1f));
				buffer[offset++] = (byte) (0x80 + (ch & 0x3f));
			} else {
				buffer[offset++] = (byte) (0xe0 + ((ch >> 12) & 0xf));
				buffer[offset++] = (byte) (0x80 + ((ch >> 6) & 0x3f));
				buffer[offset++] = (byte) (0x80 + (ch & 0x3f));
			}
		}
		if (offset != arrayLength) {
			byte[] copy = new byte[offset];
			System.arraycopy(buffer, 0, copy, 0, offset);
			return copy;
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
			int b = buffer[offset++] & 0xFF;
			switch (b >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				chars[charIndex] = (char) b;
				break;
			case 12:
			case 13:
				chars[charIndex] = (char) ((b & 0x1F) << 6 | buffer[offset++] & 0x3F);
				break;
			case 14:
				chars[charIndex] = (char) ((b & 0x0F) << 12 | (buffer[offset++] & 0x3F) << 6 | buffer[offset++] & 0x3F);
				break;
			}
			charIndex++;
		}
		return new String(chars, 0, charIndex);
	}
}
