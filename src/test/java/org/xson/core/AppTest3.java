package org.xson.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.xson.core.util.XsonTypeUtils;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianSerializerOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class AppTest3 {

	public static class StringVo implements Serializable {

		private static final long serialVersionUID = 2395065611856681050L;

		private String str1;

		private String str2;

		private String str3;

		private String str4;

		public String getStr1() {
			return str1;
		}

		public void setStr1(String str1) {
			this.str1 = str1;
		}

		public String getStr2() {
			return str2;
		}

		public void setStr2(String str2) {
			this.str2 = str2;
		}

		public String getStr3() {
			return str3;
		}

		public void setStr3(String str3) {
			this.str3 = str3;
		}

		public String getStr4() {
			return str4;
		}

		public void setStr4(String str4) {
			this.str4 = str4;
		}

		private int i1;

		private int i2;

		private int i3;

		public int getI1() {
			return i1;
		}

		public void setI1(int i1) {
			this.i1 = i1;
		}

		public int getI2() {
			return i2;
		}

		public void setI2(int i2) {
			this.i2 = i2;
		}

		public int getI3() {
			return i3;
		}

		public void setI3(int i3) {
			this.i3 = i3;
		}

	}

	@Before
	public void init() {
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("org.xson.core.testmodel.XUser7", "x7");
		prop.put("org.xson.core.testmodel.XUser6", "x6");
		prop.put("org.xson.core.AppTest3$StringVo", "v1");
		XsonSupport.addCustomAgreementType(prop);
		registration = kryo.register(StringVo.class);
	}
	
	int count = 100;
	Kryo kryo = new Kryo();
	Registration registration = null;
	public void testKryoSe(StringVo vo){
		
		// kryo.setReferences(true);
		// kryo.setRegistrationRequired(true);
		// kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		//注册类
		//Registration registration = kryo.register(StringVo.class);
		long start = 0L;
		long end = 0L;
		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			//序列化
			Output output = new Output(1, 4096);
			kryo.writeObject(output, vo);
			//byte[] bb = output.toBytes();
			output.toBytes();
			output.flush();
		}
		end = System.nanoTime();
		System.out.println("KRYO:" + (end - start));
		
	}
	
	public void testKryoSe1(StringVo vo){
		Output output = new Output(1, 4096);
		kryo.writeObject(output, vo);
		byte[] bb = output.toBytes();
		output.flush();
		
		System.out.println("KRYO LENGTH:" + bb.length);
		Input input = new Input(bb);
		StringVo svo = (StringVo) kryo.readObject(input, registration.getType());
		System.out.println(JSON.toJSON(svo));
		input.close();
	}

	@Test
	public void test() {
		StringVo vo = new StringVo();
		vo.setStr1("str.1");
		vo.setStr2("str.中国");
		vo.setStr3("str.3");
		vo.setStr4("str.4");

//		 vo.setI1(1);
//		 vo.setI2(2);
//		 vo.setI3(26);

		byte[] data = XSON.encode(vo);
		System.out.println("data.length : " + data.length);
		System.out.println("=========================================");

		String json = JSON.toJSONString(vo);
		json = JSON.toJSONString(vo);
		json = JSON.toJSONString(vo);
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

		// Object obj = XSON.parse(data);
		// StringVo vo2 = (StringVo) obj;
		// System.out.println(vo2);
		// String json = JSON.toJSONString(vo2);
		// System.out.println(json);
		// System.out.println("json.length : " + json.length());
		// System.out.println("=========================================");

		long start = 0L;
		long end = 0L;

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			XSON.encode(vo);
		}
		end = System.nanoTime();
		System.out.println("XSON:" + (end - start));

		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			JSON.toJSONString(vo);
		}
		end = System.nanoTime();
		System.out.println("JSON:" + (end - start));

		// try {
		// ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		// HessianOutput hessianOutput = new HessianOutput(bos);
		// hessianOutput.writeObject(vo);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// start = System.nanoTime();
		// for (int i = 0; i < count; i++) {
		// try {
		// ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		// HessianOutput hessianOutput = new HessianOutput(bos);
		// hessianOutput.writeObject(vo);
		// bos.toByteArray();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// end = System.nanoTime();
		// System.out.println("HESS:" + (end - start));

		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
			HessianSerializerOutput hessianOutput = new HessianSerializerOutput(
					os);
			hessianOutput.writeObject(vo);
			hessianOutput.flush();
			//System.out.println(os.toByteArray().length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start = System.nanoTime();
		for (int i = 0; i < count; i++) {
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
				HessianSerializerOutput hessianOutput = new HessianSerializerOutput(
						os);
				hessianOutput.writeObject(vo);
				hessianOutput.flush();
				os.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		end = System.nanoTime();
		System.out.println("HESS:" + (end - start));

//		testKryoSe1(vo);
//		testKryoSe(vo);
//		testKryoSe(vo);
	}

}
