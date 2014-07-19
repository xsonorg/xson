package com.github.xsonorg.generator;

import com.github.xsonorg.XsonGenerator;
import com.github.xsonorg.asm.MethodVisitor;

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
