package org.xson.generator;

import org.xson.XsonGenerator;
import org.xson.asm.MethodVisitor;

public class ShortGenerator implements XsonGenerator {

	@Override
	public void generateWriterCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKESTATIC, WRITER_DESC, "getShortDateFrame", "(S)[B");
	}

	@Override
	public void generateReaderCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, READER_DESC, "getShort", "()S");
	}

}
