package org.xson.core;

import java.util.ArrayList;
import java.util.List;

import org.xson.core.deserializer.ObjectArrayDeserializer;
import org.xson.core.deserializer.XsonDeserializerException;
import org.xson.core.generator.XsonASMDeserializerFactory;
import org.xson.core.util.ByteUtils;
import org.xson.core.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ReaderModel {

	private final byte[]	value;

	/** 总长度,包含offsetLength */
	private int				count;
	/** 数据域结束职位 */
	private int				dataEnd;
	private int				offsetLength		= 0;
	/** 下一个位置 */
	private int				index				= 0;
	private int				classInfoByteLength	= 4;
	private List<Class<?>>	classList			= null;
	private List<Object>	objectList			= new ArrayList<Object>();
	/** 类型索引, 用于用户对象类型 */
	private int				tempTypeIndex		= -1;

	ReaderModel(final byte[] value, int offset) {
		this.value = value;
		this.offsetLength = offset;
		this.index = offset;
		this.count = value.length;
	}

	public void init() {
		byte head = this.value[this.index];
		if (XsonConst.HEAD_0 == head) {
			this.dataEnd = this.count;
		} else if (XsonConst.HEAD_1 == head) {
			parseClassList();
		} else {
			throw new XsonDeserializerException("Unknown XSON protocol header : " + ByteUtils.byteToInt(head));
		}
		this.index = this.offsetLength + 1;
	}

	// private void checkValue() {
	// }

	private void parseClassList() {
		try {
			int classInfoLength = ByteUtils.byteToInt(this.value, this.count - classInfoByteLength, classInfoByteLength);
			this.index = this.count - classInfoLength - classInfoByteLength;
			this.dataEnd = this.index;
			int end = this.count - classInfoByteLength;
			classList = new ArrayList<Class<?>>();
			String className = null;
			byte b;
			while (this.index < end) {
				b = this.value[this.index++];
				if (b == XsonConst.CLASS_DES) {
					className = getString();
					classList.add(XsonTypeUtils.findClass(className));
				} else if (b == XsonConst.CLASS_REF) {
					className = getString();
					Class<?> customType = XsonSupport.getCustomAgreementType(className);
					if (null == customType) {
						throw new XsonDeserializerException("Unknown user-defined types key : " + className);
					}
					classList.add(customType);
				} else {
					throw new XsonDeserializerException("Illegal class identity : " + (b & 0xFF));
				}
			}
		} catch (Throwable e) {
			throw new XsonDeserializerException(e);
		}
	}

	public String decode(byte[] buffer, int offset, int charLength) {
		if (0 == charLength) { // 处理空字符串
			return new String();
		}
		int start = offset;
		char[] chars = new char[charLength];
		int charIndex = 0;
		for (int i = 0; i < charLength; i++) {
			int b = buffer[offset++] & 0xFF;
			if (b < 0x80) {
				chars[charIndex++] = (char) b;
			} else if ((b & 0xe0) == 0xc0) {
				chars[charIndex++] = (char) ((b & 0x1F) << 6 | buffer[offset++] & 0x3F);
			} else if ((b & 0xf0) == 0xe0) {
				chars[charIndex++] = (char) ((b & 0x0F) << 12 | (buffer[offset++] & 0x3F) << 6 | buffer[offset++] & 0x3F);
			} else {
				throw new XsonException("bad utf-8 encoding");
			}
		}
		this.incrementIndex(offset - start);
		return new String(chars);
	}

	public String decode(int offset, int charLength) {
		return decode(this.value, offset, charLength);
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

	public Class<?> getUserType(int index) {
		return this.classList.get(index);
	}

	public Class<?> getUserType() {
		return this.classList.get(this.tempTypeIndex);
	}

	public Class<?> getSystemType(int typeKey) {
		return XsonConst.SYSTEM_TYPE_MAP.get(typeKey);
	}

	public boolean isNull() {
		if (XsonConst.NULL == this.value[this.index]) {
			this.index++;
			return true;
		}
		return false;
	}

	public boolean isRef() {
		if (XsonConst.CONTROL_REF == this.value[this.index] || XsonConst.CONTROL_REF1 == this.value[this.index]) {
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
		if (this.index < this.dataEnd) {
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
		// System.out.println("appendObject:index[" + this.objectList.size() + "], Class:[" + object.getClass().getName() + "], value:" + object);
		this.objectList.add(object);
	}

	public Object getRef() {
		int i = -1;
		byte b = this.value[this.index++];
		if (XsonConst.CONTROL_REF1 == b) {
			i = ByteUtils.byteToInt(this.value[this.index++]);// 1 byte
		} else if (XsonConst.CONTROL_REF == b) {
			i = getInt();
		}
		return this.objectList.get(i);
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
		byte b = this.value[this.index];// index-->mark
		frameType = ByteUtils.byteToInt(b);

		switch (b) {
		case XsonConst.NULL:
		case XsonConst.CONTROL_CREATE_END:
		case XsonConst.CONTROL_REF:
		case XsonConst.CONTROL_REF1:
			throw new XsonDeserializerException("Illegal mark '" + Integer.toHexString(frameType) + "' at index " + this.index);
		case XsonConst.CONTROL_CREATE_USER_OBJECT1:
			typeIndex = ByteUtils.byteToInt(this.value[this.index + 1]);
			this.tempTypeIndex = typeIndex;
			this.incrementIndex(2);// index-->data
			return XsonASMDeserializerFactory.getReader(this.classList.get(typeIndex)).read(this);
		case XsonConst.CONTROL_CREATE_USER_OBJECT:
			this.incrementIndex(1);// index-->type
			typeIndex = this.getInt();
			this.tempTypeIndex = typeIndex;
			return XsonASMDeserializerFactory.getReader(this.classList.get(typeIndex)).read(this);
		case XsonConst.CONTROL_CREATE_USER_ARRAY1:
		case XsonConst.CONTROL_CREATE_USER_ARRAY:
			return getUserObjectArray();
		case XsonConst.CONTROL_CREATE_SYS_ARRAY1:
		case XsonConst.CONTROL_CREATE_SYS_ARRAY2:
		case XsonConst.CONTROL_CREATE_SYS_ARRAY:
			return getSysObjectArray();
		default:
			return getSysObject(frameType);
		}
	}

	private Object getUserObjectArray() {
		return ObjectArrayDeserializer.instance.read(this);
	}

	private Object getSysObjectArray() {
		int typeIndex = ByteUtils.byteToInt(this.value[this.index + 1]);
		int dimensions = ByteUtils.byteToInt(this.value[this.index + 2]);
		if (1 == dimensions && XsonSupport.isBasicType(typeIndex)) {
			// return XsonConst.SYSTEM_READER_MAP.get(typeIndex + ByteUtils.byteToInt(XsonConst.CONTROL_CREATE_END)).read(this);
			return XsonConst.BASE_TYPE_ARRAY_READER_MAP.get(typeIndex).read(this);
		}
		return ObjectArrayDeserializer.instance.read(this);
	}

	public Object getSysObject(int typeIndex) {
		XsonReader reader = XsonConst.SYSTEM_READER_MAP.get(typeIndex);
		if (null == reader) {
			throw new XsonDeserializerException("Unknown system reader index: " + typeIndex);
		}
		return reader.read(this);
	}

	// public void wrapToBasic() {
	// this.value[this.index] = (byte) (this.value[this.index] & XsonConst.WRAP_MASK);
	// }

	public Byte getByteWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		byte x = getByte();
		this.value[markIndex] = b;
		return x;
	}

	public byte getByte() {
		byte b = this.value[this.index + 1];
		this.incrementIndex(2);
		return b;
	}

	public Short getShortWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		short x = getShort();
		this.value[markIndex] = b;
		return x;
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
			throw new XsonDeserializerException("Illegal short mark '" + Integer.toHexString(b & 0xFF) + "' at index " + this.index);
		}
		return i;
	}

	public Integer getIntWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		int x = getInt();
		this.value[markIndex] = b;
		return x;
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
			throw new XsonDeserializerException("Illegal int mark '" + Integer.toHexString(b & 0xFF) + "' at index " + this.index);
		}
		return i;
	}

	public Long getLongWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		long x = getLong();
		this.value[markIndex] = b;
		return x;
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
			throw new XsonDeserializerException("Illegal long mark '" + Integer.toHexString(b & 0xFF) + "' at index " + this.index);
		}
		return i;
	}

	public Float getFloatWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		float x = getFloat();
		this.value[markIndex] = b;
		return x;
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
			throw new XsonDeserializerException("Illegal float mark '" + Integer.toHexString(b & 0xFF) + "' at index " + this.index);
		}
		return i;
	}

	public Double getDoubleWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		double x = getDouble();
		this.value[markIndex] = b;
		return x;
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
			throw new XsonDeserializerException("Illegal double mark '" + Integer.toHexString(b & 0xFF) + "' at index " + this.index);
		}
		return i;
	}

	public Boolean getBooleanWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		boolean x = getBoolean();
		this.value[markIndex] = b;
		return x;
	}

	public boolean getBoolean() {
		byte b = this.value[this.index + 1];
		this.incrementIndex(2);
		if (b == XsonConst.TRUE) {
			return true;
		}
		return false;
	}

	public Character getCharWrap() {
		int markIndex = this.index;
		byte b = this.value[markIndex];
		this.value[markIndex] = (byte) (this.value[markIndex] & XsonConst.WRAP_MASK);
		char x = getChar();
		this.value[markIndex] = b;
		return x;
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
			throw new XsonDeserializerException("Illegal char mark '" + Integer.toHexString(b & 0xFF) + "' at index " + this.index);
		}
		return i;
	}

	public String getString() {
		byte b = this.value[this.index++];
		int charLength = 0;
		switch (b) {
		case XsonConst.STRING1:// 1byte
			charLength = ByteUtils.byteToInt(value, this.index, 1);
			this.incrementIndex(1);
			break;
		case XsonConst.STRING2:// 2byte
			charLength = ByteUtils.byteToInt(value, this.index, 2);
			this.incrementIndex(2);
			break;
		case XsonConst.STRING3:// 3byte
			charLength = ByteUtils.byteToInt(value, this.index, 3);
			this.incrementIndex(3);
			break;
		default:// 4byte
			charLength = ByteUtils.byteToInt(value, this.index, 4);
			this.incrementIndex(4);
		}
		return decode(this.index, charLength);
	}
}
