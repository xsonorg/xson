package org.xson.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.vo.Person;
import org.xson.core.vo.PersonInfo;
import org.xson.core.vo.PersonStatus;
import org.xson.core.vo.Phone;

import com.alibaba.fastjson.JSON;

public class TestPeople {

	static Person person;

	static {
		person = new Person();
		person.setPersonId("superman111");
		person.setLoginName("superman");
		person.setEmail("sm@1.com");
		person.setPenName("pname");
		person.setStatus(PersonStatus.ENABLED);

		ArrayList<Phone> phones = new ArrayList<Phone>();
		Phone phone1 = new Phone("86", "0571", "87654321", "001");
		Phone phone2 = new Phone("86", "0571", "87654322", "002");
		phones.add(phone1);
		phones.add(phone2);
		PersonInfo pi = new PersonInfo();
		pi.setPhones(phones);
		// Phone fax = new Phone("86", "0571", "87654321", null);
		// pi.setFax(fax);
		// FullAddress addr = new FullAddress("CN", "zj", "3480", "wensanlu", "315000");
		// pi.setFullAddress(addr);
		// pi.setMobileNo("13584652131");
		// pi.setMale(true);
		// pi.setDepartment("b2b");
		// pi.setHomepageUrl("www.capcom.com");
		// pi.setJobTitle("qa");
		// pi.setName("superman");
		person.setInfoProfile(pi);
	}

	@Before
	public void init() {
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("org.xson.core.testmodel.XUser7", "x7");
		prop.put("org.xson.core.testmodel.XUser6", "x6");
		prop.put("org.xson.core.AppTest3$StringVo", "v1");
		// prop.put("org.xson.vo.Person", "p1");
		XsonSupport.addCustomAgreementType(prop);
	}

	@Test
	public void testPerson() {
		byte[] data = XSON.encode(person);
		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");

		String json = JSON.toJSONString(person);
		System.out.println(json);
		System.out.println("json.length : " + json.length());
		System.out.println("=========================================");

		// XsonTypeUtils.writeClass(data, "BYTE_DATA");
		// Person obj = XSON.parse(data);
		// System.out.println(JSON.toJSONString(obj));

		int count = 2000;
		long start = 0L;
		long end = 0L;

		List<byte[]> copyBuf = new ArrayList<byte[]>();
		for (int i = 0; i < count; i++) {
			byte[] cpData = new byte[data.length];
			System.arraycopy(data, 0, cpData, 0, data.length);
			copyBuf.add(cpData);
		}

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			XSON.encode(person);
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			JSON.toJSONString(person);
		}
		end = System.nanoTime();
		System.out.println("JSON:" + (end - start));

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			XSON.decode(copyBuf.get(i));
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			JSON.parseObject(json, Person.class);
		}
		end = System.nanoTime();
		System.out.println("JSON:" + (end - start));
	}
}
