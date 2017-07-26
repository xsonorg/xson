package org.xson.core;

import org.xson.core.asm.MethodVisitor;
import org.xson.core.asm.Opcodes;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public interface XsonGenerator extends Opcodes {

	String	WRITER_DESC	= "org/xson/core/XsonSupport";
	String	READER_DESC	= "org/xson/core/ReaderModel";

	// public void generateWriterCode(MethodVisitor mv);

	public void generateReaderCode(MethodVisitor mv);

}
