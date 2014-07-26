package com.github.xsonorg;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.github.xsonorg.codecs.CharsetUtils;
import com.github.xsonorg.codecs.XsonStringCodecs;
import com.github.xsonorg.deserializer.XsonDeserializerException;
import com.github.xsonorg.generator.XsonASMDeserializerFactory;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ReaderModel {

	private Charset charset = null;

	private final byte[] value;

	private int index = 0;

	private int count;

	private int dataCount;

	private List<Class<?>> classList;

	private List<Object> objectList = new ArrayList<Object>();

	ReaderModel(final byte[] value) {
		this(value, CharsetUtils.UTF8);
	}

	// ReaderModel(final byte[] value, String charsetName) {
	// this.value = value;
	// if (null == charsetName) {
	// charsetName = "UTF-8";
	// }
	// this.charset = CharsetUtils.lookup(charsetName);
	// }

	ReaderModel(final byte[] value, Charset charset) {
		this.value = value;
		this.charset = charset;
	}

	public void init() {
		this.count = this.value.length;
		byte head = this.value[0];
		boolean hasClass = true;
		switch (head) {
		case XsonConst.HEAD_0:
			this.dataCount = this.count - 1;
			hasClass = false;
			this.index = 1;
			break;
		case XsonConst.HEAD_1:
			this.dataCount = ByteUtils.byteToInt(this.value, 1, 1);
			this.index = 2;
			break;
		case XsonConst.HEAD_2:
			this.dataCount = ByteUtils.byteToInt(this.value, 1, 2);
			this.index = 3;
			break;
		case XsonConst.HEAD_3:
			this.dataCount = ByteUtils.byteToInt(this.value, 1, 3);
			this.index = 4;
			break;
		case XsonConst.HEAD_4:
			this.dataCount = ByteUtils.byteToInt(this.value, 1, 4);
			this.index = 5;
			break;
		case XsonConst.NULL:
			return;
		default:
			throw new XsonDeserializerException(
					"Unknown XSON protocol header : "
							+ ByteUtils.byteToInt(head));
		}
		// checkValue();
		if (hasClass) {
			parseClassList();
		}
	}

	// private void checkValue() {
	// }

	private void parseClassList() {
		try {
			classList = new ArrayList<Class<?>>();
			int pos = this.index + this.dataCount;
			int fullNameLength = 0;
			String fullName = null;
			byte b;
			while (pos < this.count) {
				b = this.value[pos++];
				fullNameLength = ByteUtils.byteToInt(this.value[pos++]);
				// fullName = new String(this.value, pos, fullNameLength);
				fullName = decode(this.value, pos, fullNameLength,
						CharsetUtils.ASCII);
				if (b == XsonConst.CLASS_DES) {
					classList.add(XsonTypeUtils.findClass(fullName));
				} else if (b == XsonConst.CLASS_REF) {
					Class<?> customType = XsonSupport
							.getCustomAgreementType(fullName);
					if (null == customType) {
						throw new XsonDeserializerException(
								"Unknown user-defined types key : " + fullName);
					}
					classList.add(customType);
				} else {
					throw new XsonDeserializerException(
							"Illegal class identity : " + (b & 0xFF));
				}
				pos += fullNameLength;
			}
		} catch (Exception e) {
			throw new XsonDeserializerException(e);
		}
	}

	public String decode(byte[] buf, int offset, int length) {
		return XsonStringCodecs.decode(charset, buf, offset, length);
	}

	public String decode(byte[] buf, int offset, int length, Charset charset) {
		return XsonStringCodecs.decode(charset, buf, offset, length);
	}

	public byte[] getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}

	public void incrementIndex(int delta) {
		this.index += delta;
	}

	public Charset getCharset() {
		return charset;
	}

	public Class<?> getUserType(int index) {
		return this.classList.get(index);
	}

	public boolean isNull() {
		if (XsonConst.NULL == this.value[this.index]) {
			this.index++;
			return true;
		}
		return false;
	}

	public boolean isRef() {
		if (XsonConst.CONTROL_REF == this.value[this.index]
				|| XsonConst.CONTROL_REF1 == this.value[this.index]) {
			return true;
		}
		return false;
	}

	public boolean isEnd() {
		if (XsonConst.CONTROL_CREATE_END == this.value[this.index]) {
			return true;
		}
		return false;
	}

	public boolean isBound() {
		if (this.index < this.count) {
			return true;
		}
		return false;
	}

	public void endObject() {
		if (XsonConst.CONTROL_CREATE_END == this.value[this.index]) {
			this.index++;
		}
	}

	public void appendObject(Object object) {
		this.objectList.add(object);
	}

	public Object getRef() {
		int i = 0;
		if (XsonConst.CONTROL_REF1 == this.value[this.index]) {
			i = ByteUtils.byteToInt(this.value[this.index + 1]);
			this.incrementIndex(2);// 1+1
		} else {
			i = ByteUtils.byteToInt(this.value, this.index + 1, 4);
			this.incrementIndex(5);// 4+1
		}
		return this.objectList.get(i);
	}

	public Class<?> getUserType() {
		int typeIndex = ByteUtils.byteToInt(this.value[this.index - 1]);
		return this.classList.get(typeIndex);
	}

	public Object getObject() {
		if (isNull()) {
			return null;
		} else if (isRef()) {
			return getRef();
		}
		return resolveCurrentObject();
	}

	private Object resolveCurrentObject() {

		int frameType = 0;
		int typeIndex = 0;
		byte b = this.value[this.index];// index-->type
		frameType = ByteUtils.byteToInt(b);

		switch (b) {
		case XsonConst.NULL:
		case XsonConst.CONTROL_CREATE_END:
		case XsonConst.CONTROL_REF:
		case XsonConst.CONTROL_REF1:
			throw new XsonDeserializerException("Illegal mark '"
					+ Integer.toHexString(frameType) + "' at index "
					+ this.index);
		case XsonConst.CONTROL_CREATE_USER_OBJECT:
			typeIndex = ByteUtils.byteToInt(this.value[this.index + 1]);
			this.incrementIndex(2);// typeIndex frameType
			// Object returnValue = XsonASMDeserializerFactory.getReader(
			// this.classList.get(typeIndex)).read(this);
			// this.endObject();
			// return returnValue;
			return XsonASMDeserializerFactory.getReader(
					this.classList.get(typeIndex)).read(this);
		case XsonConst.CONTROL_CREATE_USER_ARRAY1:
		case XsonConst.CONTROL_CREATE_USER_ARRAY:
			return XsonASMDeserializerFactory.getUserObjectArrayReader().read(
					this);
		case XsonConst.CONTROL_CREATE_SYS_ARRAY1:
		case XsonConst.CONTROL_CREATE_SYS_ARRAY2:
		case XsonConst.CONTROL_CREATE_SYS_ARRAY3:
		case XsonConst.CONTROL_CREATE_SYS_ARRAY:
			return XsonASMDeserializerFactory.getSysObjectArrayReader(this)
					.read(this);
		default:
			return XsonASMDeserializerFactory.getReader(frameType).read(this);
		}
	}

	public void wrapToBasic() {
		this.value[this.index] = (byte) (this.value[this.index] & XsonConst.WRAP_MASK);
	}

	public byte getByte() {
		byte b = this.value[this.index + 1];
		this.incrementIndex(2);
		return b;
	}

	public short getShort() {
		short i = 0;
		byte b = this.value[this.index++];
		switch (b) {
		case XsonConst.SHORT1:
			i = ByteUtils.byteToShort(this.value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.SHORT:
			i = ByteUtils.byteToShort(this.value, this.index, 2);
			this.incrementIndex(2);
			break;
		default:
			throw new XsonDeserializerException("Illegal short mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index "
					+ this.index);
		}
		return i;
	}

	public int getInt() {
		int i = 0;
		byte b = this.value[this.index++];
		switch (b) {
		case XsonConst.INT1:
			i = ByteUtils.byteToInt(this.value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.INT2:
			i = ByteUtils.byteToInt(this.value, this.index, 2);
			this.incrementIndex(2);
			break;
		case XsonConst.INT3:
			i = ByteUtils.byteToInt(this.value, this.index, 3);
			this.incrementIndex(3);
			break;
		case XsonConst.INT:
			i = ByteUtils.byteToInt(this.value, this.index, 4);
			this.incrementIndex(4);
			break;
		default:
			throw new XsonDeserializerException("Illegal int mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index "
					+ this.index);
		}
		return i;
	}

	public long getLong() {
		long i = 0L;
		byte b = this.value[this.index++];
		switch (b) {
		case XsonConst.LONG1:
			i = ByteUtils.byteToLong(this.value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.LONG2:
			i = ByteUtils.byteToLong(this.value, this.index, 2);
			this.incrementIndex(2);
			break;
		case XsonConst.LONG3:
			i = ByteUtils.byteToLong(this.value, this.index, 3);
			this.incrementIndex(3);
			break;
		case XsonConst.LONG4:
			i = ByteUtils.byteToLong(this.value, this.index, 4);
			this.incrementIndex(4);
			break;
		case XsonConst.LONG5:
			i = ByteUtils.byteToLong(this.value, this.index, 5);
			this.incrementIndex(5);
			break;
		case XsonConst.LONG6:
			i = ByteUtils.byteToLong(this.value, this.index, 6);
			this.incrementIndex(6);
			break;
		case XsonConst.LONG7:
			i = ByteUtils.byteToLong(this.value, this.index, 7);
			this.incrementIndex(7);
			break;
		case XsonConst.LONG:
			i = ByteUtils.byteToLong(this.value, this.index, 8);
			this.incrementIndex(8);
			break;
		default:
			throw new XsonDeserializerException("Illegal long mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index "
					+ this.index);
		}
		return i;
	}

	public float getFloat() {
		float i = 0;
		byte b = this.value[this.index++];
		switch (b) {
		case XsonConst.FLOAT1:
			i = ByteUtils.byteToFloat(this.value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.FLOAT2:
			i = ByteUtils.byteToFloat(this.value, this.index, 2);
			this.incrementIndex(2);
			break;
		case XsonConst.FLOAT3:
			i = ByteUtils.byteToFloat(this.value, this.index, 3);
			this.incrementIndex(3);
			break;
		case XsonConst.FLOAT:
			i = ByteUtils.byteToFloat(this.value, this.index, 4);
			this.incrementIndex(4);
			break;
		default:
			throw new XsonDeserializerException("Illegal float mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index "
					+ this.index);
		}
		return i;
	}

	public double getDouble() {
		double i = 0D;
		byte b = this.value[this.index++];
		switch (b) {
		case XsonConst.DOUBLE1:
			i = ByteUtils.byteToDouble(this.value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.DOUBLE2:
			i = ByteUtils.byteToDouble(this.value, this.index, 2);
			this.incrementIndex(2);
			break;
		case XsonConst.DOUBLE3:
			i = ByteUtils.byteToDouble(this.value, this.index, 3);
			this.incrementIndex(3);
			break;
		case XsonConst.DOUBLE4:
			i = ByteUtils.byteToDouble(this.value, this.index, 4);
			this.incrementIndex(4);
			break;
		case XsonConst.DOUBLE5:
			i = ByteUtils.byteToDouble(this.value, this.index, 5);
			this.incrementIndex(5);
			break;
		case XsonConst.DOUBLE6:
			i = ByteUtils.byteToDouble(this.value, this.index, 6);
			this.incrementIndex(6);
			break;
		case XsonConst.DOUBLE7:
			i = ByteUtils.byteToDouble(this.value, this.index, 7);
			this.incrementIndex(7);
			break;
		case XsonConst.DOUBLE:
			i = ByteUtils.byteToDouble(this.value, this.index, 8);
			this.incrementIndex(8);
			break;
		default:
			throw new XsonDeserializerException("Illegal double mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index "
					+ this.index);
		}
		return i;
	}

	public boolean getBoolean() {
		byte b = this.value[this.index + 1];
		this.incrementIndex(2);
		if (b == XsonConst.TRUE) {
			return true;
		}
		return false;
	}

	public char getChar() {
		char i;
		byte b = this.value[this.index++];
		switch (b) {
		case XsonConst.CHAR1:
			i = ByteUtils.byteToChar(this.value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.CHAR:
			i = ByteUtils.byteToChar(this.value, this.index, 2);
			this.incrementIndex(2);
			break;
		default:
			throw new XsonDeserializerException("Illegal char mark '"
					+ Integer.toHexString(b & 0xFF) + "' at index "
					+ this.index);
		}
		return i;
	}

}
