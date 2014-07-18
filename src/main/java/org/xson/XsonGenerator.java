package org.xson;

import org.xson.asm.MethodVisitor;
import org.xson.asm.Opcodes;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public interface XsonGenerator extends Opcodes {

	String WRITER_DESC = "org/xson/XsonSupport";

	String READER_DESC = "org/xson/ReaderModel";

	public void generateWriterCode(MethodVisitor mv);

	public void generateReaderCode(MethodVisitor mv);

}
