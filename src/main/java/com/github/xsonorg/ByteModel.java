package com.github.xsonorg;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.xsonorg.codecs.CharsetUtils;
import com.github.xsonorg.codecs.XsonStringCodecs;
import com.github.xsonorg.generator.XsonASMSerializerFactory;
import com.github.xsonorg.serializer.XsonSerializerException;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.ObjectHashMap;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteModel {

	private Charset charset = null;

	private List<byte[]> dataList = null;

	private List<Class<?>> classList = new ArrayList<Class<?>>();

	private Map<Class<?>, Integer> repeatClassMap = new HashMap<Class<?>, Integer>();

	// private Map<Object, Integer> repeatObjectMap = new HashMap<Object,
	// Integer>();
	private ObjectHashMap<Object, Integer> repeatObjectMap = new ObjectHashMap<Object, Integer>(64);

	private int repeatObjectIndex = 0;

	private int count = 0;

	ByteModel() {
		this(CharsetUtils.UTF8);
	}

	// ByteModel(String charsetName) {
	// if (null == charsetName) {
	// charsetName = "UTF-8";
	// }
	// this.charset = CharsetUtils.lookup(charsetName);
	// dataList = new ArrayList<byte[]>(32);
	// }

	ByteModel(Charset charset) {
		this.charset = charset;
		dataList = new ArrayList<byte[]>(32);
	}

	public Charset getCharset() {
		return charset;
	}

	public void appendNull() {
		this.append(XsonConst.NULL_OBJECT_BYTES);
	}

	public void appendEnd() {
		this.append(XsonConst.CONTROL_CREATE_END);
	}

	public void append(byte buf) {
		dataList.add(new byte[] { buf });
		this.count += 1;
	}

	public void append(byte[] buf) {
		if (null == buf) {
			appendNull();
			return;
		}
		dataList.add(buf);
		this.count += buf.length;
	}

	public byte[] getData() {
		if (0 == this.count) {
			return XsonConst.NULL_OBJECT_BYTES;
		}
		int dataDomainCount = this.count;

		boolean hasClass = writeDependClasses();
		// boolean hasClass = false;

		byte[] value = null;
		int pos = 0;

		if (hasClass) {
			byte[] temp = ByteUtils.intToByte(dataDomainCount);
			int headLength = temp.length + 1;
			value = new byte[this.count + headLength];
			if (2 == headLength) {
				value[0] = XsonConst.HEAD_1;
				value[1] = temp[0];
			} else if (3 == headLength) {
				value[0] = XsonConst.HEAD_2;
				value[1] = temp[0];
				value[2] = temp[1];
			} else if (4 == headLength) {
				value[0] = XsonConst.HEAD_3;
				value[1] = temp[0];
				value[2] = temp[1];
				value[3] = temp[2];
			} else {
				value[0] = XsonConst.HEAD_4;
				value[1] = temp[0];
				value[2] = temp[1];
				value[3] = temp[2];
				value[4] = temp[3];
			}
			pos = headLength;
		} else {
			value = new byte[this.count + 1];
			value[0] = XsonConst.HEAD_0;
			pos = 1;
		}
		int size = dataList.size();
		for (int i = 0; i < size; i++) {
			byte[] item = dataList.get(i);
			int length = item.length;
			for (int j = 0; j < length; j++) {
				value[pos++] = item[j];
			}
			// ArrayCopy
			// System.arraycopy(item, 0, value, pos, length);
			// pos += length;
		}
		return value;
	}

	private boolean writeDependClasses() {
		int size = classList.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				Class<?> clazz = classList.get(i);
				String caKey = XsonSupport.getCustomAgreementKey(clazz);
				if (null == caKey) {
					// byte[] strBuf = clazz.getName().getBytes(this.charset);
					// byte[] strBuf = encode(clazz.getName());
					byte[] strBuf = encode(clazz.getName(), CharsetUtils.ASCII);
					append(new byte[] { XsonConst.CLASS_DES,
							(byte) strBuf.length });
					append(strBuf);
				} else {
					// byte[] strBuf = caKey.getBytes(this.charset);
					// byte[] strBuf = encode(caKey);
					byte[] strBuf = encode(caKey, CharsetUtils.ASCII);
					append(new byte[] { XsonConst.CLASS_REF,
							(byte) strBuf.length });
					append(strBuf);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public byte[] encode(String val) {
		return XsonStringCodecs.encode(charset, val);
	}

	public byte[] encode(String val, Charset charset) {
		return XsonStringCodecs.encode(charset, val);
	}

	private int getClassIndex(Class<?> clazz) {
		Integer classIndex = repeatClassMap.get(clazz);
		if (null == classIndex) {
			classList.add(clazz);
			classIndex = classList.size() - 1;
			repeatClassMap.put(clazz, classIndex);
		}
		return classIndex.intValue();
	}

	public void appendCreateUserObject(Class<?> clazz) {
		int typeIndex = this.getClassIndex(clazz);
		if (typeIndex > 255) {
			throw new XsonSerializerException(
					"UserObjectFrame : 该版本类型支持的最大索引 : 255 ,当前index : "
							+ typeIndex);
		}
		byte[] buf = { XsonConst.CONTROL_CREATE_USER_OBJECT, (byte) typeIndex };
		this.append(buf);
	}

	public void appendCreateSystemObject(int typeIndex) {
		if (typeIndex > 255) {
			throw new XsonSerializerException(
					"SysObjectFrame : 该版本类型支持的最大索引 : 255 ,当前index : "
							+ typeIndex);
		}
		this.append((byte) typeIndex);
	}

	public void appendCreateObject(Class<?> clazz) {
		int typeIndex = XsonSupport.getFieldTypeConst(clazz);
		if (typeIndex > 0) {
			this.appendCreateSystemObject(typeIndex);
		} else {
			this.appendCreateUserObject(clazz);
		}
	}

	public void appendCreateSystemArray(byte type, int dimensions, int length) {
		if (length < 255) {
			byte[] buf = { XsonConst.CONTROL_CREATE_SYS_ARRAY1, type,
					(byte) dimensions, (byte) length };
			this.append(buf);
		} else {// > 255
			byte[] heads = { XsonConst.CONTROL_CREATE_SYS_ARRAY2, type,
					(byte) dimensions };
			byte[] lens = ByteUtils.intToByte(length);
			if (lens.length == 3) {
				heads[0] = XsonConst.CONTROL_CREATE_SYS_ARRAY3;
			} else if (lens.length == 4) {
				heads[0] = XsonConst.CONTROL_CREATE_SYS_ARRAY;
			}
			this.append(heads);
			this.append(lens);
		}
	}

	public void appendCreateUserArray(Class<?> clazz, int dimensions, int length) {
		if (length < 255) {
			byte[] buf = { XsonConst.CONTROL_CREATE_USER_ARRAY1,
					(byte) getClassIndex(clazz), (byte) dimensions,
					(byte) length };
			this.append(buf);
		} else {
			byte[] heads = { XsonConst.CONTROL_CREATE_USER_ARRAY,
					(byte) getClassIndex(clazz), (byte) dimensions };
			byte[] lens = ByteUtils.intTo4Byte(length);
			this.append(heads);
			this.append(lens);
		}
	}

	public void appendCreateArray(Class<?> clazz, int dimensions, int length) {
		int typeIndex = XsonSupport.getFieldTypeConst(clazz);
		if (typeIndex > 0) {
			this.appendCreateSystemArray((byte) typeIndex, dimensions, length);
		} else {
			this.appendCreateUserArray(clazz, dimensions, length);
		}
	}

	public void appendRefData(Object object) {
		int objectIndex = repeatObjectMap.get(object).intValue();
		byte[] buf = null;
		if (objectIndex < 255) {
			buf = new byte[] { XsonConst.CONTROL_REF1, (byte) objectIndex };
		} else {
			buf = ByteUtils.intTo4ByteWithMark(objectIndex);
			buf[0] = XsonConst.CONTROL_REF;
		}
		this.append(buf);
	}

	public boolean checkRefData(Object object) {
		if (null == repeatObjectMap.get(object)) {
			repeatObjectMap.put(object, repeatObjectIndex++);
			return false;
		}
		return true;
	}

	public void writeObject(Object object) {
		if (object == null) {
			this.appendNull();
		} else if (this.checkRefData(object)) {
			this.appendRefData(object);
		} else {
			XsonASMSerializerFactory.getWriter(object).write(object, this);
		}
	}

}
