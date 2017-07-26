package org.xson.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.util.XsonTypeUtils;

import com.alibaba.fastjson.JSON;

public class TestMap {

	public static class MapVo implements Serializable {

		private Object[] objects;

		public Object[] getObjects() {
			return objects;
		}

		public void setObjects(Object[] objects) {
			this.objects = objects;
		}

	}

	@Before
	public void init() {
		 Map<String, String> prop = new HashMap<String, String>();
		 prop.put("org.xson.core.testmodel.XUser7", "x7");
		 prop.put("org.xson.core.testmodel.XUser6", "x6");
		 prop.put("org.xson.core.AppTest3$StringVo", "v1");
		 prop.put("org.xson.core.TestMap$MapVo", "v2");
		 prop.put("java.util.HashMap", "m1");
		 XsonSupport.addCustomAgreementType(prop);
	}

	int count = 100;

	@Test
	public void test() {
		MapVo vo = new MapVo();
		// Map<String, String>[] maps = new HashMap[2];
		// maps[0] = new HashMap<String, String>();
		// maps[0].put("a1", "b1");
		// maps[0].put("a2", "b2");
		// maps[1] = new HashMap<String, String>();
		// maps[1].put("a1", "b1");
		// maps[1].put("a2", "b2");

		Map<Map[], Map[]>[] maps = new HashMap[2];
		maps[0] = new HashMap<Map[], Map[]>();

		Map[] m1 = new HashMap[2];
		m1[0] = new HashMap();
		m1[0].put("a1", "b1");

		Map[] m2 = new HashMap[2];
		m2[0] = new HashMap();
		m2[0].put("a2", "b1");

		Map[] m3 = new HashMap[2];
		m3[0] = new HashMap();
		m3[0].put("a3", "b3");

		Map[] m4 = new HashMap[2];
		m4[0] = new HashMap();
		m4[0].put("a4", "b2");

		maps[0].put(m1, m2);
		maps[0].put(m3, m4);

		// maps[1] = new HashMap<Map[], Map[]>();
		// maps[1].put("a1", "b1");
		// maps[1].put("a2", "b2");

		Object[] objects = maps;
		vo.setObjects(objects);

		byte[] data = XSON.encode(vo);
		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");

		String json = JSON.toJSONString(vo);
		System.out.println(json);
		System.out.println("json.length : " + json.length());
		System.out.println("=========================================");

		XsonTypeUtils.writeClass(data, "BYTE_DATA");

		List<byte[]> copyBuf = new ArrayList<byte[]>();
		for (int i = 0; i < count; i++) {
			byte[] cpData = new byte[data.length];
			System.arraycopy(data, 0, cpData, 0, data.length);
			copyBuf.add(cpData);
		}

		Object obj = XSON.decode(data);
		Object vo2 = (MapVo) obj;
		System.out.println(JSON.toJSONString(vo2));
		System.out.println("=========================================");

		long start = 0L;
		long end = 0L;

		// start = System.nanoTime();
		// for (int i = 0; i < count; i++) {
		// XSON.write(vo);
		// }
		// end = System.nanoTime();
		// System.out.println("XSON:" + (end - start));
		//
		// start = System.nanoTime();
		// for (int i = 0; i < count; i++) {
		// JSON.toJSONString(vo);
		// }
		// end = System.nanoTime();
		// System.out.println("JSON:" + (end - start));

	}
}
