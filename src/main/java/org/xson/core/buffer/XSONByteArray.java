package org.xson.core.buffer;

import java.util.ArrayList;
import java.util.List;

import org.xson.core.XsonConst;

/**
 * XSON序列号专用
 */
public class XSONByteArray {

	private static ByteArrayManager	manager		= XsonConst.byteArrayManager;
	private List<ByteArrayItem>		otherItem	= null;
	private ByteArrayItem			item		= null;
	private int						index		= 0;
	/** 整体索引, setLimit之前赋值 */
	private int						allIndex	= 0;

	public XSONByteArray() {
		this(0);
	}

	public XSONByteArray(int offset) {
		item = manager.allocate(index);
		if (offset > 0) {
			index = offset;
		}
		// TODO 暂不考虑 offset>item.capacity
		// for (int i = 0; i < offset; i++) {
		// // set 0
		// }
	}

	private byte[] getBuffer(int requiredCapacity) {
		int remainingCapacity = getItemRemainingCapacity();
		if (remainingCapacity >= requiredCapacity) {
			return item.getArray();
		}
		increaseItem(requiredCapacity);
		return item.getArray();
	}

	private void increaseItem(int requiredCapacity) {
		if (null == otherItem) {
			otherItem = new ArrayList<ByteArrayItem>();
		}

		allIndex += this.index;

		// 记录使用的
		item.setLimit(this.index);
		otherItem.add(item);
		// item = manager.allocate(requiredCapacity, this.index);
		item = manager.allocate(requiredCapacity, this.allIndex);

		this.index = 0;
	}

	private int getItemRemainingCapacity() {
		return item.getRemainingCapacity(this.index);
	}

	public void writeByte1(byte x) {
		byte[] buffer = getBuffer(1);
		buffer[index++] = x;
	}

	public void writeByte(byte x) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = XsonConst.BYTE;
		buffer[index++] = x;
	}

	public void writeByte(byte x, byte mask) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = (byte) (XsonConst.BYTE | mask);
		buffer[index++] = x;
	}

	public void writeBoolean(boolean x) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = XsonConst.BOOLEAN;
		buffer[index++] = (x) ? XsonConst.TRUE : XsonConst.FALSE;
	}

	public void writeBoolean(boolean x, byte mask) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = (byte) (XsonConst.BOOLEAN | mask);
		buffer[index++] = (x) ? XsonConst.TRUE : XsonConst.FALSE;
	}

	public void writeShort2(short x) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = (byte) (x >> 8);
		buffer[index++] = (byte) x;
	}

	public void writeShort(short x) {
		if ((x & 0xFF00) == 0) { // 前1字节为空
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.SHORT1;
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.SHORT;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeShort(short x, byte mask) {
		if ((x & 0xFF00) == 0) { // 前1字节为空
			byte[] buffer = getBuffer(2);
			buffer[index++] = (byte) (XsonConst.SHORT1 | mask);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(3);
			buffer[index++] = (byte) (XsonConst.SHORT | mask);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeInt(int x) {
		if ((x & 0xFFFFFF00) == 0) { // 前3字节为空
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.INT1;
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF0000) == 0) { // 前2字节为空
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.INT2;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF000000) == 0) { // 前1字节为空
			byte[] buffer = getBuffer(4);
			buffer[index++] = XsonConst.INT3;
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(5);
			buffer[index++] = XsonConst.INT;
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeInt(int x, byte mask) {
		if ((x & 0xFFFFFF00) == 0) { // 前3字节为空
			byte[] buffer = getBuffer(2);
			buffer[index++] = (byte) (XsonConst.INT1 | mask);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF0000) == 0) { // 前2字节为空
			byte[] buffer = getBuffer(3);
			buffer[index++] = (byte) (XsonConst.INT2 | mask);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF000000) == 0) { // 前1字节为空
			byte[] buffer = getBuffer(4);
			buffer[index++] = (byte) (XsonConst.INT3 | mask);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(5);
			buffer[index++] = (byte) (XsonConst.INT | mask);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeInt1(int x) {
		byte[] buffer = getBuffer(1);
		buffer[index++] = (byte) x;
	}

	public void writeInt2(int x) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = (byte) (x >> 8);
		buffer[index++] = (byte) x;
	}

	public void writeInt4(int x) {
		byte[] buffer = getBuffer(4);
		buffer[index++] = (byte) (x >> 24);
		buffer[index++] = (byte) (x >> 16);
		buffer[index++] = (byte) (x >> 8);
		buffer[index++] = (byte) x;
	}

	public void writeLong(long x) {
		if ((x & 0xFFFFFFFFFFFFFF00L) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.LONG1;
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFFFF0000L) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.LONG2;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFF000000L) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = XsonConst.LONG3;
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFF00000000L) == 0) {
			byte[] buffer = getBuffer(5);
			buffer[index++] = XsonConst.LONG4;
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFF0000000000L) == 0) {
			byte[] buffer = getBuffer(6);
			buffer[index++] = XsonConst.LONG5;
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF000000000000L) == 0) {
			byte[] buffer = getBuffer(7);
			buffer[index++] = XsonConst.LONG6;
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF00000000000000L) == 0) {
			byte[] buffer = getBuffer(8);
			buffer[index++] = XsonConst.LONG7;
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(9);
			buffer[index++] = XsonConst.LONG;
			buffer[index++] = (byte) (x >> 56);
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeLong(long x, byte mask) {
		if ((x & 0xFFFFFFFFFFFFFF00L) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = (byte) (XsonConst.LONG1 | mask);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFFFF0000L) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = (byte) (XsonConst.LONG2 | mask);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFF000000L) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = (byte) (XsonConst.LONG3 | mask);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFF00000000L) == 0) {
			byte[] buffer = getBuffer(5);
			buffer[index++] = (byte) (XsonConst.LONG4 | mask);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFF0000000000L) == 0) {
			byte[] buffer = getBuffer(6);
			buffer[index++] = (byte) (XsonConst.LONG5 | mask);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF000000000000L) == 0) {
			byte[] buffer = getBuffer(7);
			buffer[index++] = (byte) (XsonConst.LONG6 | mask);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF00000000000000L) == 0) {
			byte[] buffer = getBuffer(8);
			buffer[index++] = (byte) (XsonConst.LONG7 | mask);
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(9);
			buffer[index++] = (byte) (XsonConst.LONG | mask);
			buffer[index++] = (byte) (x >> 56);
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeFloat(float f) {
		int x = Float.floatToIntBits(f);
		if ((x & 0xFFFFFF00) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.FLOAT1;
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF0000) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.FLOAT2;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF000000) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = XsonConst.FLOAT3;
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(5);
			buffer[index++] = XsonConst.FLOAT;
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeFloat(float f, byte mask) {
		int x = Float.floatToIntBits(f);
		if ((x & 0xFFFFFF00) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = (byte) (XsonConst.FLOAT1 | mask);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF0000) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = (byte) (XsonConst.FLOAT2 | mask);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF000000) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = (byte) (XsonConst.FLOAT3 | mask);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(5);
			buffer[index++] = (byte) (XsonConst.FLOAT | mask);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeDouble(double d) {
		long x = Double.doubleToLongBits(d);
		if ((x & 0xFFFFFFFFFFFFFF00L) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.DOUBLE1;
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFFFF0000L) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.DOUBLE2;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFF000000L) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = XsonConst.DOUBLE3;
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFF00000000L) == 0) {
			byte[] buffer = getBuffer(5);
			buffer[index++] = XsonConst.DOUBLE4;
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFF0000000000L) == 0) {
			byte[] buffer = getBuffer(6);
			buffer[index++] = XsonConst.DOUBLE5;
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF000000000000L) == 0) {
			byte[] buffer = getBuffer(7);
			buffer[index++] = XsonConst.DOUBLE6;
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF00000000000000L) == 0) {
			byte[] buffer = getBuffer(8);
			buffer[index++] = XsonConst.DOUBLE7;
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(9);
			buffer[index++] = XsonConst.DOUBLE;
			buffer[index++] = (byte) (x >> 56);
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeDouble(double d, byte mask) {
		long x = Double.doubleToLongBits(d);
		if ((x & 0xFFFFFFFFFFFFFF00L) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = (byte) (XsonConst.DOUBLE1 | mask);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFFFF0000L) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = (byte) (XsonConst.DOUBLE2 | mask);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFFFF000000L) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = (byte) (XsonConst.DOUBLE3 | mask);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFFFF00000000L) == 0) {
			byte[] buffer = getBuffer(5);
			buffer[index++] = (byte) (XsonConst.DOUBLE4 | mask);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFFFF0000000000L) == 0) {
			byte[] buffer = getBuffer(6);
			buffer[index++] = (byte) (XsonConst.DOUBLE5 | mask);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF000000000000L) == 0) {
			byte[] buffer = getBuffer(7);
			buffer[index++] = (byte) (XsonConst.DOUBLE6 | mask);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF00000000000000L) == 0) {
			byte[] buffer = getBuffer(8);
			buffer[index++] = (byte) (XsonConst.DOUBLE7 | mask);
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(9);
			buffer[index++] = (byte) (XsonConst.DOUBLE | mask);
			buffer[index++] = (byte) (x >> 56);
			buffer[index++] = (byte) (x >> 48);
			buffer[index++] = (byte) (x >> 40);
			buffer[index++] = (byte) (x >> 32);
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeChar(char x) {
		if ((x & 0xFF00) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.CHAR1;
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.CHAR;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeChar(char x, byte mask) {
		if ((x & 0xFF00) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = (byte) (XsonConst.CHAR1 | mask);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(3);
			buffer[index++] = (byte) (XsonConst.CHAR | mask);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
	}

	public void writeChar2(char x) {
		byte[] buffer = getBuffer(2);
		buffer[index++] = (byte) (x >> 8);
		buffer[index++] = (byte) x;
	}

	// public void writeString1(String val) {
	// int x = val.length();// char length
	// if ((x & 0xFFFFFF00) == 0) {
	// byte[] buffer = getBuffer(2);
	// buffer[index++] = XsonConst.STRING1;
	// buffer[index++] = (byte) x;
	// } else if ((x & 0xFFFF0000) == 0) {
	// byte[] buffer = getBuffer(3);
	// buffer[index++] = XsonConst.STRING2;
	// buffer[index++] = (byte) (x >> 8);
	// buffer[index++] = (byte) x;
	// } else if ((x & 0xFF000000) == 0) {
	// byte[] buffer = getBuffer(4);
	// buffer[index++] = XsonConst.STRING3;
	// buffer[index++] = (byte) (x >> 16);
	// buffer[index++] = (byte) (x >> 8);
	// buffer[index++] = (byte) x;
	// } else {
	// byte[] buffer = getBuffer(5);
	// buffer[index++] = XsonConst.STRING;
	// buffer[index++] = (byte) (x >> 24);
	// buffer[index++] = (byte) (x >> 16);
	// buffer[index++] = (byte) (x >> 8);
	// buffer[index++] = (byte) x;
	// }
	// // empty string
	// if (0 == x) {
	// return;
	// }
	//
	// int remainingCapacity = 0;
	// int start = 0;
	// int can = 0;
	// while (true) {
	// // 查看Item剩余容量
	// remainingCapacity = getItemRemainingCapacity();
	// if (remainingCapacity < 3) {
	// increaseItem((x - start) * 3);// 新建Item
	// remainingCapacity = getItemRemainingCapacity();
	// }
	// // 计算可以处理的长度
	// can = remainingCapacity / 3;
	// if ((start + can) >= x) {
	// byte[] buffer = item.getArray();
	// for (int i = start; i < x; i++) {
	// char ch = val.charAt(i);
	// if (ch < 0x80) {
	// buffer[index++] = (byte) (ch);
	// } else if (ch < 0x800) {
	// buffer[index++] = (byte) (0xc0 + ((ch >> 6) & 0x1f));
	// buffer[index++] = (byte) (0x80 + (ch & 0x3f));
	// } else {
	// buffer[index++] = (byte) (0xe0 + ((ch >> 12) & 0xf));
	// buffer[index++] = (byte) (0x80 + ((ch >> 6) & 0x3f));
	// buffer[index++] = (byte) (0x80 + (ch & 0x3f));
	// }
	// }
	// } else {
	// byte[] buffer = item.getArray();
	// can = 0;
	// int r1 = remainingCapacity - 1;
	// int r2 = remainingCapacity - 2;
	// int r3 = remainingCapacity - 3;
	// for (int i = start; i < x; i++) {
	// char ch = val.charAt(i);
	// if (ch < 0x80) {
	// if (r1 < can) {
	// break;
	// }
	// buffer[index++] = (byte) (ch);
	// can += 1;
	// } else if (ch < 0x800) {
	// if (r2 < can) {
	// break;
	// }
	// buffer[index++] = (byte) (0xc0 + ((ch >> 6) & 0x1f));
	// buffer[index++] = (byte) (0x80 + (ch & 0x3f));
	// can += 2;
	// } else {
	// if (r3 < can) {
	// break;
	// }
	// buffer[index++] = (byte) (0xe0 + ((ch >> 12) & 0xf));
	// buffer[index++] = (byte) (0x80 + ((ch >> 6) & 0x3f));
	// buffer[index++] = (byte) (0x80 + (ch & 0x3f));
	// can += 3;
	// }
	// }
	// }
	// start += can;
	// if (start >= x) {
	// break;
	// }
	// }
	// }

	public void writeString(String val) {
		int x = val.length();// char length
		if ((x & 0xFFFFFF00) == 0) {
			byte[] buffer = getBuffer(2);
			buffer[index++] = XsonConst.STRING1;
			buffer[index++] = (byte) x;
		} else if ((x & 0xFFFF0000) == 0) {
			byte[] buffer = getBuffer(3);
			buffer[index++] = XsonConst.STRING2;
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else if ((x & 0xFF000000) == 0) {
			byte[] buffer = getBuffer(4);
			buffer[index++] = XsonConst.STRING3;
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		} else {
			byte[] buffer = getBuffer(5);
			buffer[index++] = XsonConst.STRING;
			buffer[index++] = (byte) (x >> 24);
			buffer[index++] = (byte) (x >> 16);
			buffer[index++] = (byte) (x >> 8);
			buffer[index++] = (byte) x;
		}
		// empty string
		if (0 == x) {
			return;
		}

		int remainingCapacity = 0;
		int start = 0;
		int end = 0;
		int can = 0;
		while (true) {
			// 查看Item剩余容量
			remainingCapacity = getItemRemainingCapacity();
			if (remainingCapacity < 3) {
				increaseItem((x - start) * 3);// 新建Item
				remainingCapacity = getItemRemainingCapacity();
			}
			// 计算可以处理的长度
			can = remainingCapacity / 3;
			end = start + can;
			if (end > x) {
				end = x;
			}

			byte[] buffer = item.getArray();
			for (int i = start; i < end; i++) {
				char ch = val.charAt(i);
				if (ch < 0x80)
					buffer[index++] = (byte) (ch);
				else if (ch < 0x800) {
					buffer[index++] = (byte) (0xc0 + ((ch >> 6) & 0x1f));
					buffer[index++] = (byte) (0x80 + (ch & 0x3f));
				} else {
					buffer[index++] = (byte) (0xe0 + ((ch >> 12) & 0xf));
					buffer[index++] = (byte) (0x80 + ((ch >> 6) & 0x3f));
					buffer[index++] = (byte) (0x80 + (ch & 0x3f));
				}
			}
			start += can;
			if (start >= x) {
				break;
			}
		}
	}

	// boolean mark
	public void writeByteArray(byte[] x) {
		writeByteArray(x, false);
	}

	// boolean mark
	public void writeByteArray(byte[] x, boolean copy) {
		if (copy) {
			byte[] buffer = getBuffer(x.length);
			System.arraycopy(x, 0, buffer, index, x.length);
			this.index += x.length;
			return;
		}

		if (null == otherItem) {
			otherItem = new ArrayList<ByteArrayItem>();
		}

		allIndex += this.index;

		// 记录使用的
		item.setLimit(this.index);
		otherItem.add(item);

		// System.out.println("HH:index=" + this.index + ",allIndex=" + this.allIndex);

		// 伪装成ByteArrayItem
		// item = new ByteArrayItem(manager, x, this.index, false, -1);
		item = new ByteArrayItem(manager, x, this.allIndex, false, -1);
		// this.index += x.length;
		this.index = x.length;
	}

	// public byte[] toBytes() {
	// item.setLimit(this.index);
	// byte[] result = new byte[this.index];
	// if (null != otherItem) {
	// otherItem.add(item);
	// item = null;
	// for (int i = 0; i < otherItem.size(); i++) {
	// ByteArrayItem temp = otherItem.get(i);
	// int limit = temp.getLimit();
	// if (limit > 0) {
	// System.arraycopy(temp.getArray(), 0, result, temp.getBaIndex(), limit);
	// }
	// temp.recycle();
	// }
	// otherItem.clear();
	// otherItem = null;
	// } else {
	// byte[] buffer = item.getArray();
	// System.arraycopy(buffer, 0, result, 0, this.index);
	// item.recycle();
	// item = null;
	// }
	// return result;
	// }

	public byte[] toBytes() {

		this.allIndex += this.index;

		item.setLimit(this.index);
		byte[] result = new byte[this.allIndex];
		if (null != otherItem) {
			otherItem.add(item);
			item = null;
			for (int i = 0; i < otherItem.size(); i++) {
				ByteArrayItem temp = otherItem.get(i);
				int limit = temp.getLimit();
				if (limit > 0) {
					System.arraycopy(temp.getArray(), 0, result, temp.getBaIndex(), limit);
				}
				temp.recycle();
			}
			otherItem.clear();
			otherItem = null;
		} else {
			byte[] buffer = item.getArray();
			System.arraycopy(buffer, 0, result, 0, this.allIndex);
			item.recycle();
			item = null;
		}
		return result;
	}

	// public static String byteArrayToString(byte[] buffer) {
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < buffer.length; i++) {
	// if (i > 0) {
	// sb.append(" ");
	// }
	// byte b = buffer[i];
	// String hex = Integer.toHexString(b & 0xFF);
	// sb.append(hex);
	// }
	// return sb.toString();
	// }

	// public static String decode(byte[] buffer, int offset, int charLength) {
	// if (0 == charLength) { // 处理空字符串
	// return new String();
	// }
	// char[] chars = new char[charLength];
	// int charIndex = 0;
	// for (int i = 0; i < charLength; i++) {
	// int b = buffer[offset++] & 0xFF;
	// if (b < 0x80) {
	// chars[charIndex++] = (char) b;
	// } else if ((b & 0xe0) == 0xc0) {
	// chars[charIndex++] = (char) ((b & 0x1F) << 6 | buffer[offset++] & 0x3F);
	// } else if ((b & 0xf0) == 0xe0) {
	// chars[charIndex++] = (char) ((b & 0x0F) << 12 | (buffer[offset++] & 0x3F) << 6 | buffer[offset++] & 0x3F);
	// } else {
	// throw new XsonException("bad utf-8 encoding");
	// }
	// }
	// return new String(chars);
	// }

	public int getIndex() {
		return this.allIndex + index;
	}

}
