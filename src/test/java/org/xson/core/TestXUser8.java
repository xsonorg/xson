package org.xson.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.testmodel.XUser8;
import org.xson.core.util.XsonTypeUtils;
import org.xson.core.vo.Phone;

import com.alibaba.fastjson.JSON;

public class TestXUser8 {
	private XUser8 user = new XUser8();

	@Before
	public void init() {
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("org.xson.core.testmodel.XUser7", "x7");
		prop.put("org.xson.core.testmodel.XUser6", "x6");
		prop.put("org.xson.core.AppTest3$StringVo", "v1");
		//prop.put("org.xson.vo.Person", "p1");
		
		prop.put("org.xson.core.testmodel.XUser8", "a");
		prop.put("java.util.ArrayList", "b");
		prop.put("org.xson.core.vo.Phone", "c");
		 

		XsonSupport.addCustomAgreementType(prop);

		ArrayList<Phone> phones = new ArrayList<Phone>();
		Phone phone1 = new Phone("86", "0571", "87654321", "001");
		Phone phone2 = new Phone("86", "0571", "87654322", "002");
		
		phones.add(phone1);
		//phones.add(phone2);
		user.setPhones(phones);
	}

	@Test
	public void testPerson() {
		byte[] data = XSON.encode(user);
		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");

		String json = JSON.toJSONString(user);
		System.out.println(json);
		System.out.println("json.length : " + json.length());
		System.out.println("=========================================");

		XsonTypeUtils.writeClass(data, "BYTE_DATA");
		Object obj = XSON.decode(data);
		System.out.println(JSON.toJSONString(obj));

	}
}
