package org.xson.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BufferTest {

	private int size = 1024;

	int count = 100;

	private String str = "abc中国abc中国abc中国abc中国abc中国abc中国abc中国";

	long time1 = 0l;
	
	long time2 = 0l;
	
	public void printTest(){
		System.out.println("time1:" + time1);
		System.out.println("time2:" + time2);
	}
	
	public void test01(boolean isPrint) {
		byte[] buf = new byte[size];
		long start = 0L;
		long end = 0L;
		int rc = 0;
		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			byte[] tmp = Utf8Encode.encode(str);
			if ((rc + tmp.length) > buf.length) {
				byte[] nbuf = new byte[buf.length + size];
				System.arraycopy(buf, 0, nbuf, 0, rc);
				buf = nbuf;
			}
			System.arraycopy(tmp, 0, buf, rc, tmp.length);
			rc += tmp.length;
		}

		if (buf.length > rc) {
			byte[] nbuf = new byte[rc];
			System.arraycopy(buf, 0, nbuf, 0, rc);
		}
		end = System.nanoTime();
		
		
		
		if (isPrint) {
			//System.out.println("test01:" + (end - start));
			time1 += (end - start);
		}
	}

	public void test02(boolean isPrint) {
		List<byte[]> bufList = new ArrayList<byte[]>();
		long start = 0L;
		long end = 0L;
		int rc = 0;
		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			byte[] tmp = Utf8Encode.encode(str);
			rc += tmp.length;
			bufList.add(tmp);
		}
		byte[] buf = new byte[rc];
		rc = 0;
		for (int i = 0; i < count; i++) {
			byte[] tmp = bufList.get(i);
//			System.arraycopy(tmp, 0, buf, rc, tmp.length);
//			rc += tmp.length;
			
			for (int j = 0; j < tmp.length; j++) {
				buf[rc++] = tmp[j];
			}
			
		}
		end = System.nanoTime();
		
		
		
		if (isPrint) {
			//System.out.println("test02:" + (end - start));
			time2 += (end - start);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			test01(false);
			test02(false);
		}

		for (int i = 0; i < 10000; i++) {
			test01(true);
			test02(true);
			//System.out.println();
		}
		
		printTest();
		System.out.println();
	}
}
