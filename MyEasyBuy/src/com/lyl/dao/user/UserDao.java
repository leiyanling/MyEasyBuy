package com.lyl.dao.user;

import java.sql.SQLException;

import com.lyl.entity.User;

public interface UserDao {

	int add(User user) throws Exception;// 新增用户信息

	int update(User user) throws Exception;// 更新用户信息

	public User getUser(String loginName)throws SQLException ;

}
