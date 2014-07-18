package org.xson.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.xson.XsonSupport;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class FieldModel implements Comparable<FieldModel> {

	// 字段名
	private String name;

	private String getterName;

	private String setterName;

	private Class<?> fieldClass;

	private int fieldTypeConst;

	private String ownerDesc;

	private String getterDesc;

	private String setterDesc;

	private boolean baseType;

	public FieldModel(Field field, Method getter, Method setter, Class<?> clazz) {
		this.name = field.getName();
		this.getterName = getter.getName();
		this.setterName = setter.getName();

		this.ownerDesc = XsonTypeUtils.toAsmClass(clazz.getName());
		this.getterDesc = XsonTypeUtils.getDesc(getter);
		this.setterDesc = XsonTypeUtils.getDesc(setter);

		this.fieldClass = field.getType();

		init();
	}

	private void init() {
		this.fieldTypeConst = XsonSupport.getFieldTypeConst(this.fieldClass);
		this.baseType = XsonSupport.isBasicType(this.fieldTypeConst);
	}

	public String getName() {
		return name;
	}

	public boolean isBaseType() {
		return baseType;
	}

	public String getOwnerDesc() {
		return ownerDesc;
	}

	public String getGetterDesc() {
		return getterDesc;
	}

	public Class<?> getFieldClass() {
		return fieldClass;
	}

	public int getFieldTypeConst() {
		return fieldTypeConst;
	}

	public String getGetterName() {
		return getterName;
	}

	public String getSetterName() {
		return setterName;
	}

	public String getSetterDesc() {
		return setterDesc;
	}

	@Override
	public int compareTo(FieldModel o) {
		return this.name.compareTo(o.name);
	}

}
