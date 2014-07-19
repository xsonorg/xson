package com.github.xsonorg.generator;

import com.github.xsonorg.XsonGenerator;
import com.github.xsonorg.asm.MethodVisitor;

public class DoubleGenerator implements XsonGenerator {

	@Override
	public void generateWriterCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKESTATIC, WRITER_DESC, "getDoubleDateFrame", "(D)[B");
	}

	@Override
	public void generateReaderCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, READER_DESC, "getDouble", "()D");
	}

}
