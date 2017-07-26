package org.xson.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestString {

	int count = 100;
	long start = 0L;
	long end = 0L;

	String str = "abc";

	public void test01() throws Exception {
		List<byte[]> bufs = new ArrayList<byte[]>();

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			bufs.add(str.getBytes("UTF-8"));
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));
	}

	public void test02() throws Exception {
		List<byte[]> bufs = new ArrayList<byte[]>();
		StringBuilder sb = new StringBuilder();
		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			sb.append(str);
		}
		bufs.add(sb.toString().getBytes("UTF-8"));
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));

	}

	@Test
	public void test() {
		try {
			test01();
			test02();
			System.out.println();
			test01();
			test02();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
