package com.lyl.service.user;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.lyl.entity.User;

public class UserServiceImplTest {
	private UserServiceImpl userService = new UserServiceImpl();

	@Test
	public void testAdd() {

	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testLogin() {
		User user = userService.login("admin", "123456");
		Assert.assertTrue(user != null);
	}

}
