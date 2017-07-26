package org.xson.core;

import org.junit.Assert;
import org.junit.Test;
import org.xson.core.pojo.User;

public class TestApp {

	@Test
	public void test01() {
		User user1 = new User();
		user1.setId(18);
		//user1.setName("david中国");
		user1.setName(new String());

		byte[] data = XSON.encode(user1);

		User user2 = XSON.decode(data);
		Assert.assertTrue(user2.getId() == 18);
		Assert.assertTrue(user1.getName().equals(user2.getName()));
	}

}
