package org.xson.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xson.core.buffer.XSONByteArray;
import org.xson.core.generator.XsonASMSerializerFactory;
import org.xson.core.serializer.XsonSerializerException;
import org.xson.core.util.ObjectHashMap;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class WriterModel {

	private List<Class<?>>					classList			= new ArrayList<Class<?>>();
	private Map<Class<?>, Integer>			repeatClassMap		= new HashMap<Class<?>, Integer>();
	private ObjectHashMap<Object, Integer>	repeatObjectMap		= new ObjectHashMap<Object, Integer>(64);
	private int								repeatObjectIndex	= 0;
	/** 整体的偏移 */
	private int								offsetLength		= 0;
	private XSONByteArray					byteArray			= null;

	WriterModel() {
		this(0);
	}

	WriterModel(int offsetLength) {
		this.offsetLength = offsetLength;
		byteArray = new XSONByteArray(offsetLength);
		byteArray.writeByte1(XsonConst.HEAD_0);
	}

	/////////////////////////////////////////////////////////////////////

	public void writeByte(byte val) {
		byteArray.writeByte(val);
	}

	public void writeByte(byte val, byte mask) {
		byteArray.writeByte(val, mask);
	}

	public void writeByte1(byte val) {
		byteArray.writeByte1(val);
	}

	public void writeBoolean(boolean val) {
		byteArray.writeBoolean(val);
	}

	public void writeBoolean(boolean val, byte mask) {
		byteArray.writeBoolean(val, mask);
	}

	public void writeShort(short val) {
		byteArray.writeShort(val);
	}

	public void writeShort(short val, byte mask) {
		byteArray.writeShort(val, mask);
	}

	public void writeShort2(short val) {
		byteArray.writeShort2(val);
	}

	public void writeChar(char val) {
		byteArray.writeChar(val);
	}

	public void writeChar(char val, byte mask) {
		byteArray.writeChar(val, mask);
	}

	public void writeChar2(char val) {
		byteArray.writeChar2(val);
	}

	public void writeInt(int val) {
		byteArray.writeInt(val);
	}

	public void writeInt(int val, byte mask) {
		byteArray.writeInt(val, mask);
	}

	public void writeLong(long val) {
		byteArray.writeLong(val);
	}

	public void writeLong(long val, byte mask) {
		byteArray.writeLong(val, mask);
	}

	public void writeFloat(float val) {
		byteArray.writeFloat(val);
	}

	public void writeFloat(float val, byte mask) {
		byteArray.writeFloat(val, mask);
	}

	public void writeDouble(double val) {
		byteArray.writeDouble(val);
	}

	public void writeDouble(double val, byte mask) {
		byteArray.writeDouble(val, mask);
	}

	public void writeString(String val) {
		byteArray.writeString(val);
	}

	public void writeByteArray(byte[] val, boolean copy) {
		byteArray.writeByteArray(val, copy);
	}

	public void writeNull() {
		byteArray.writeByte1(XsonConst.NULL);
	}

	public void writeEnd() {
		byteArray.writeByte1(XsonConst.CONTROL_CREATE_END);
	}

	/////////////////////////////////////////////////////////////////////

	public byte[] getData() {
		// this.count不可能==0
		int classInfoLength = writeDependClasses();
		if (classInfoLength > 0) {
			byteArray.writeInt4(classInfoLength);
		}
		byte[] data = byteArray.toBytes();
		if (classInfoLength > 0) {
			data[this.offsetLength] = XsonConst.HEAD_1;
		}
		byteArray = null;
		return data;
	}

	private int writeDependClasses() {
		int size = classList.size();
		if (size > 0) {
			int start = byteArray.getIndex();
			for (int i = 0; i < size; i++) {
				Class<?> clazz = classList.get(i);
				String caKey = XsonSupport.getCustomAgreementKey(clazz);
				if (null == caKey) {
					byteArray.writeByte1(XsonConst.CLASS_DES);
					byteArray.writeString(clazz.getName());
				} else {
					byteArray.writeByte1(XsonConst.CLASS_REF);
					byteArray.writeString(caKey);
				}
			}
			return byteArray.getIndex() - start;
		}
		return 0;
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

	/** 添加一个用户对象,开始: */
	public void appendCreateUserObject(Class<?> clazz) {
		int typeIndex = this.getClassIndex(clazz);
		if (typeIndex < 256) {
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_USER_OBJECT1);
			byteArray.writeByte1((byte) typeIndex);
		} else {
			// 突破255限制
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_USER_OBJECT);
			byteArray.writeInt(typeIndex);
		}
	}

	/** 添加一个系统对象,开始: */
	public void appendCreateSystemObject(int typeIndex) {
		if (typeIndex > 255) {// TODO 这里不一定是255
			throw new XsonSerializerException("SysObjectFrame : 该版本类型支持的最大索引 : 255 ,当前index : " + typeIndex);
		}
		byteArray.writeByte1((byte) typeIndex);
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
		if ((length & 0xFFFFFF00) == 0) { // length 1byte
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_SYS_ARRAY1);
			byteArray.writeByte1((byte) type);
			byteArray.writeByte1((byte) dimensions);
			byteArray.writeInt1(length);
		} else if ((length & 0xFFFF0000) == 0) { // length 2byte
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_SYS_ARRAY2);
			byteArray.writeByte1((byte) type);
			byteArray.writeByte1((byte) dimensions);
			byteArray.writeInt2(length);
		} else {
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_SYS_ARRAY);
			byteArray.writeByte1((byte) type);
			byteArray.writeByte1((byte) dimensions);
			byteArray.writeInt4(length);
		}

	}

	public void appendCreateUserArray(Class<?> clazz, int dimensions, int length) {
		// 这里有变动, 支持更多的用户类型
		if ((length & 0xFFFFFF00) == 0) {
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_USER_ARRAY1);
			byteArray.writeInt(getClassIndex(clazz));
			byteArray.writeByte1((byte) dimensions);
			byteArray.writeInt1(length);
		} else {
			byteArray.writeByte1(XsonConst.CONTROL_CREATE_USER_ARRAY);
			byteArray.writeInt(getClassIndex(clazz));
			byteArray.writeByte1((byte) dimensions);
			byteArray.writeInt4(length);
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
		if (objectIndex < 256) {
			byteArray.writeByte1(XsonConst.CONTROL_REF1);
			byteArray.writeByte1((byte) objectIndex);
		} else {
			byteArray.writeByte1(XsonConst.CONTROL_REF);
			byteArray.writeInt(objectIndex);
		}
	}

	public boolean checkRefData(Object object) {
		if (null == repeatObjectMap.get(object)) {
			// System.out.println("add Obj: index:[" + repeatObjectIndex + "], " + object.getClass() );
			repeatObjectMap.put(object, repeatObjectIndex++);
			return false;
		}
		return true;
	}

	public void writeObject(Object object) {
		if (object == null) {
			this.writeNull();
		} else if (this.checkRefData(object)) {
			this.appendRefData(object);
		} else {
			XsonASMSerializerFactory.getWriter(object).write(object, this);
		}
	}

}
