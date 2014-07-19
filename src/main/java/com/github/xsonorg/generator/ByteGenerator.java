package com.github.xsonorg.generator;

import com.github.xsonorg.XsonGenerator;
import com.github.xsonorg.asm.MethodVisitor;

public class ByteGenerator implements XsonGenerator {

	@Override
	public void generateWriterCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKESTATIC, WRITER_DESC, "getByteDateFrame", "(B)[B");
	}

	@Override
	public void generateReaderCode(MethodVisitor mv) {
		mv.visitMethodInsn(INVOKEVIRTUAL, READER_DESC, "getByte", "()B");
	}
	
}
