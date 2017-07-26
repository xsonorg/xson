package org.xson.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.testmodel.XUser7;
import org.xson.core.util.XsonTypeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class AppTest {

	@Before
	public void init() {
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("org.xson.core.testmodel.XUser7", "x7");
		XsonSupport.addCustomAgreementType(prop);
		// XsonConst.USER_READER_MAP.put(XUser7.class, new FullTypeBeanReader());
	}

	@Test
	public void testXUser7() {
		XUser7 user = new XUser7();
		user.setV1((byte) 126);
		user.setV2((short) -1238);
		user.setV3(222);
		user.setV4(12L);
		user.setV5(5.1F);
		user.setV6(0.3333D);
		user.setV7(false);
		user.setV8('A');
		user.setName("abc");
		user.setName1("abc1");

		user.setB1((byte) 128);
		user.setB2(false);
		user.setB3((short) -3);
		user.setB4('B');
		user.setB5(128);
		user.setB6((long) 128);
		user.setB7(1.3F);
		user.setB8(0.32333339D);
		user.setB51(128);

		user.setArr1(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5 });
		user.setArr2(new short[] { (short) 11, (short) 12, (short) 13, (short) 14, (short) 15 });
		user.setArr4(new char[] { '你', '好', '你', '你' });
		user.setArr3(new boolean[] { true, false, true, true, true });
		user.setArr5(new int[] { 123, 2323, 232323, -2323 });
		user.setArr6(new long[] { 123, 2323, 232323, -2323 });
		user.setArr7(new float[] { 123, 2323, 232323, -2323 });
		user.setArr8(new double[] { 1.23, 23.23, 23.2323, -2.323 });

		int[][] aa1 = { { 1, 23 }, null, { 1, 4, 5, 6, 7 } };
		user.setArr51(aa1);

		int[][][] aa2 = { { { 23, 45 }, { 22, 8, 9, 0 } }, { { 23, 45 }, { 22, 8, 9, 0 } }, { { 23, 45 }, { 22, 8, 9, 0 } } };
		user.setArr52(aa2);

		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("abc");
		list.add("abc1");
		user.setList(list);

		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("b", 3);
		user.setMap(map);

		// user.setEa(EnumA.B);s

		user.setS(34);
		String bigVal = "3432432432432432432432432";
		user.setBi(new BigInteger(bigVal));
		user.setCls(XUser7.class);

		String bigDVal = "234324324324324324334.32432432432432432432432";
		user.setDi(new BigDecimal(bigDVal));

		StringBuffer sb1 = new StringBuffer();
		sb1.append("234324324324324324334.32432432432432432432432");
		user.setSb1(sb1);

		StringBuilder sb2 = new StringBuilder();
		sb2.append("234324324324324324334.32432432432432432432432");
		user.setSb2(sb2);

		try {
			user.setU1(URI.create("http://java.sun.com/j2se/1.3/docs/guide/collections/designfaq.html"));
			user.setU2(new URL("http://java.sun.com/j2se/1.3/docs/guide/collections/designfaq.html"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		user.setUu(UUID.randomUUID());

		user.setLo(Locale.CHINESE);
		user.setCu(Currency.getInstance(Locale.FRANCE));
		user.setTz(TimeZone.getTimeZone("Asia/Shanghai"));

		try {
			user.setAd4((Inet4Address) InetAddress.getByName("192.168.1.2"));
			user.setAd6((Inet6Address) InetAddress.getByName("fe80::a8d1:6079:6c01:65fc%14"));
			user.setAd(InetAddress.getByName("fe80::a8d1:6079:6c01:65fc%14"));
			user.setAd(InetAddress.getByName("192.168.1.2"));
			user.setIsad(new InetSocketAddress("192.168.1.2", 12));
			// user.setIsad(new
			// InetSocketAddress(InetAddress.getByName("192.168.1.2"), 65535));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		java.util.Date d1 = new java.util.Date(-62346039000L);
		// System.out.println("d1.getTime()=" + d1.getTime());
		// System.out.println("d1.getTime()=" + d1.toLocaleString());
		user.setD1(d1);

		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		// System.out.println("d2.getTime()=" + d2.getTime());
		user.setD2(d2);

		java.sql.Time d3 = new java.sql.Time(d1.getTime());
		// System.out.println("d3.getTime()=" + d3.getTime());
		user.setD3(d3);

		java.sql.Timestamp d4 = new java.sql.Timestamp(d1.getTime());
		// System.out.println("d4.getTime()=" + d4.getTime());
		user.setD4(d4);

		byte[] data = XSON.encode(user);

		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");

		// MyIoUtil.writeClass(data, "BYTE_DATA");
		XsonTypeUtils.writeClass(data, "BYTE_DATA");

		// List<byte[]> copyBuf = new ArrayList<byte[]>();
		// for (int i = 0; i < 100; i++) {
		// byte[] cpData = new byte[data.length];
		// System.arraycopy(data, 0, cpData, 0, data.length);
		// copyBuf.add(cpData);
		// }

		List<byte[]> copyBuf = new ArrayList<byte[]>();
		for (int i = 0; i < 100; i++) {
			// byte[] cpData = new byte[data.length];
			// System.arraycopy(data, 0, cpData, 0, data.length);
			copyBuf.add(data);
		}

		Object obj = XSON.decode(data);
		XUser7 user2 = (XUser7) obj;

		String json = JSON.toJSONString(user2, SerializerFeature.WriteClassName);
		System.out.println(json);
		System.out.println("json.length : " + json.length());

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

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			XSON.decode(copyBuf.get(i));
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));

		// start = System.nanoTime();
		// for (int i = 0; i < count; i++) {
		// JSON.parseObject(json, XUser7.class);
		// }
		// end = System.nanoTime();
		// System.out.println("JSON PARSE:" + (end - start));

	}

	@Test
	public void testLoad() {
		XUser7 user = new XUser7();
		byte[] data = XSON.encode(user);
		XsonTypeUtils.writeClass(data, "BYTE_DATA");
		Object obj = XSON.decode(data);
		System.out.println(JSON.toJSONString(obj));
	}

	@Test
	public void testNull() {
		byte[] data = XSON.encode(null);
		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");
		// MyIoUtil.writeClass(data, "BYTE_DATA");
		XsonTypeUtils.writeClass(data, "BYTE_DATA");
		Object obj = XSON.decode(data);
		XUser7 user2 = (XUser7) obj;
		System.out.println(JSON.toJSONString(user2));
		System.out.println(JSON.toJSONString(user2).getBytes().length);
	}

	@Test
	public void testArray() {
		byte[] data = XSON.encode(new int[] { -1, 2, 3, 4, 3434, 34 });
		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");
		// MyIoUtil.writeClass(data, "BYTE_DATA");
		XsonTypeUtils.writeClass(data, "BYTE_DATA");
		Object obj = XSON.decode(data);
		System.out.println(JSON.toJSONString(obj));
		System.out.println(JSON.toJSONString(obj).length());
	}

	// @Test
	// public void testInt() {
	// int x = 1231232;
	// byte[] data = XSON.encodeInt(x);
	// System.out.println("data.length : " + data.length);
	// System.out.println("=========================================");
	// // MyIoUtil.writeClass(data, "BYTE_DATA");
	// XsonTypeUtils.writeClass(data, "BYTE_DATA");
	// int y = XSON.decodeInt(data);
	// System.out.println(JSON.toJSONString(y));
	// System.out.println("json.length : " + JSON.toJSONString(y).length());
	// }
}
