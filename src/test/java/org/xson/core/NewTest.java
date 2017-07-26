package org.xson.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xson.core.pojo.FullTypeBean;
import org.xson.core.util.XsonTypeUtils;

import com.alibaba.fastjson.JSON;

public class NewTest {

	@Test
	public void test01() {
		FullTypeBean user = new FullTypeBean();
		// user.setV1((byte) 3);
		// user.setV2((short) -1238);
		// user.setV3(222);
		// user.setV4(12L);
		// user.setV5(5.1F);
		// user.setV6(0.3333D);
		// user.setV7(false);
		// user.setV8('A');
		user.setName("abc");
		user.setName1("abc1");

		// user.setArr1(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5 });
		// user.setArr1(new byte[] { (byte) 1, });

		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("abc");
		list.add("abc1");
		user.setList(list);

		byte[] buffer = XSON.encode(user);
		System.out.println("length: " + buffer.length);
		XsonTypeUtils.writeClass(buffer, "BYTE_DATA");
		System.out.println("CREATE BYTE_DATA SUCCESS...");

		FullTypeBean userx = XSON.decode(buffer);
		System.out.println(JSON.toJSONString(user));
		System.out.println(JSON.toJSONString(userx));
	}
}
