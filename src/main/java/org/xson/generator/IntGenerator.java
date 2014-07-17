package org.xson.generator;

import org.xson.XsonGenerator;
import org.xson.asm.MethodVisitor;

public class IntGenerator implements XsonGenerator {

	@Override
	public void generateWriterCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKESTATIC, WRITER_DESC, "getIntDateFrame", "(I)[B");
	}

	@Override
	public void generateReaderCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, READER_DESC, "getInt", "()I");
	}

}
