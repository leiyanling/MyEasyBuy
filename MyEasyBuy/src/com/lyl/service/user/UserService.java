package com.lyl.service.user;

import com.lyl.entity.User;

public interface UserService {

	public boolean add(User user);

	public boolean update(User user);

	public User login(String loginName, String password);

}
