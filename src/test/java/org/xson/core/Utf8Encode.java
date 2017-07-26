package org.xson.core;

import java.util.Arrays;

public class Utf8Encode {

	public static byte[] encode(String str) {
		int length = str.length();
		int offset = 0;
		byte[] buffer = new byte[length * 3];
		for (int i = 0; i < length; i++) {
			char ch = str.charAt(i);
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
		return Arrays.copyOf(buffer, offset);
	}

}
