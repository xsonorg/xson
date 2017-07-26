package org.xson.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.testmodel.XUser6;
import org.xson.core.util.XsonTypeUtils;

import com.alibaba.fastjson.JSON;

public class AppTest2 {

	@Before
	public void init() {
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("org.xson.core.testmodel.XUser7", "x7");
		prop.put("org.xson.core.testmodel.XUser6", "x6");
		XsonSupport.addCustomAgreementType(prop);
	}

	@Test
	public void testXUser6() {
		XUser6 user = new XUser6();

		//user.setEa(EnumA.C);

		java.util.Date d1 = new java.util.Date(-62346039000L);
		user.setD1(d1);

		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		user.setD2(d2);

		java.sql.Time d3 = new java.sql.Time(d1.getTime());
		user.setD3(d3);

		java.sql.Timestamp d4 = new java.sql.Timestamp(d1.getTime());
		user.setD4(d4);

		byte[] data = XSON.encode(user);

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

//		Object obj = XSON.parse(data);
//		XUser6 user2 = (XUser6) obj;
//
//		String json = JSON.toJSONString(user2);
//		System.out.println(json);
//		System.out.println("json.length : " + json.length());

		JSON.toJSONString(user);
		JSON.toJSONString(user);
		JSON.toJSONString(user);
		
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
//
//		start = System.nanoTime();
//		for (int i = 0; i < count; i++) {
//			JSON.parseObject(json, XUser6.class);
//		}
//		end = System.nanoTime();
//		System.out.println("JSON:" + (end - start));

	}

}
