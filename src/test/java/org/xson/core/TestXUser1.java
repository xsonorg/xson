package org.xson.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.testmodel.XUser1;
import org.xson.core.userext.CustomerDeserializer;
import org.xson.core.userext.CustomerSerializer;

import com.alibaba.fastjson.JSON;

public class TestXUser1 {

	XUser1 user01 = new XUser1();

	@Before
	public void init() {
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("org.xson.core.testmodel.XUser7", "x7");
		prop.put("org.xson.core.testmodel.XUser6", "x6");
		prop.put("org.xson.core.testmodel.XUser1", "x1");
		prop.put("org.xson.core.testmodel.Abc", "a1");
		XsonSupport.addCustomAgreementType(prop);
		
		XsonSupport.addCustomSupportType(XUser1.class, new CustomerSerializer(), new CustomerDeserializer());
		
		initUser();
	}

	private void initUser() {
		user01.setId(18);
		user01.setName("abc");
		user01.setAge(20);
//		Abc abc = new Abc();
//		abc.setMail("中国@haorizi.com");
//		user01.setAbc(abc);
	}

	private void contrast(int count, Object user, List<byte[]> copyBuf,
			String json) {
		long start = 0L;
		long end = 0L;

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			XSON.encode(user);
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			JSON.toJSONString(user);
		}
		end = System.nanoTime();
		System.out.println("JSON:" + (end - start));

//		start = System.nanoTime();
//		for (int i = 0; i < count; i++) {
//			XSON.parse(copyBuf.get(i));
//		}
//		end = System.nanoTime();
//		System.out.println("XSON:" + (end - start));

//		start = System.nanoTime();
//		for (int i = 0; i < count; i++) {
//			JSON.parseObject(json, XUser6.class);
//		}
//		end = System.nanoTime();
//		System.out.println("JSON:" + (end - start));
	}

	@Test
	public void test01() {

		XUser1 user = user01;
		
		for (int i = 0; i < 10; i++) {
			XSON.encode(user);
		}
		for (int i = 0; i < 10; i++) {
			JSON.toJSONString(user);
		}
		
		int count = 100;
		long start = 0L;
		long end = 0L;

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			XSON.encode(user);
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));
		
		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			JSON.toJSONString(user);
		}
		end = System.nanoTime();
		System.out.println("JSON:" + (end - start));
		
		/*
		byte[] data = XSON.write(user);

		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");

		XsonTypeUtils.writeClass(data, "BYTE_DATA");

		int count = 100;
		List<byte[]> copyBuf = new ArrayList<byte[]>();
		for (int i = 0; i < count; i++) {
			byte[] cpData = new byte[data.length];
			System.arraycopy(data, 0, cpData, 0, data.length);
			copyBuf.add(cpData);
		}

		Object obj = XSON.parse(data);
		XUser1 user2 = (XUser1) obj;

		// String json = JSON.toJSONString(user2,
		// SerializerFeature.WriteClassName);
		String json = JSON.toJSONString(user2);
		System.out.println(json);
		System.out.println("json.length : " + json.length());
		
		contrast(count, user2, copyBuf, json);
		*/
	}
}
