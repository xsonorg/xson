package org.xson.core.generator;

import org.xson.core.XsonGenerator;
import org.xson.core.asm.MethodVisitor;

public class DoubleGenerator implements XsonGenerator {

	// @Override
	// public void generateWriterCode(MethodVisitor mv) {
	// mv.visitMethodInsn(INVOKESTATIC, WRITER_DESC, "getDoubleDateFrame", "(D)[B");
	// }

	@Override
	public void generateReaderCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, READER_DESC, "getDouble", "()D");
	}

}
