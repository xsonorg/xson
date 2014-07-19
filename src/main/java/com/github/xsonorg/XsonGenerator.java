package com.github.xsonorg;

import com.github.xsonorg.asm.MethodVisitor;
import com.github.xsonorg.asm.Opcodes;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public interface XsonGenerator extends Opcodes {

	// String WRITER_DESC = "org/xson/XsonSupport";
	//
	// String READER_DESC = "org/xson/ReaderModel";

	String WRITER_DESC = "com/github/xsonorg/XsonSupport";

	String READER_DESC = "com/github/xsonorg/ReaderModel";

	public void generateWriterCode(MethodVisitor mv);

	public void generateReaderCode(MethodVisitor mv);

}
