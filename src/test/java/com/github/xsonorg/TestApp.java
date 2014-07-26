package com.github.xsonorg;

import org.junit.Assert;
import org.junit.Test;

import com.github.xsonorg.pojo.User;

public class TestApp {

	@Test
	public void test01() {
		User user1 = new User();
		user1.setId(18);
		//user1.setName("david中国");
		user1.setName(new String());

		byte[] data = XSON.write(user1);

		User user2 = XSON.parse(data);
		Assert.assertTrue(user2.getId() == 18);
		Assert.assertTrue(user1.getName().equals(user2.getName()));
	}

}
