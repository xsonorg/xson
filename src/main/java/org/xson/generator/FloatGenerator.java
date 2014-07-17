package org.xson.generator;

import org.xson.XsonGenerator;
import org.xson.asm.MethodVisitor;

public class FloatGenerator implements XsonGenerator {

	@Override
	public void generateWriterCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKESTATIC, WRITER_DESC, "getFloatDateFrame", "(F)[B");
	}

	@Override
	public void generateReaderCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, READER_DESC, "getFloat", "()F");
	}
}
