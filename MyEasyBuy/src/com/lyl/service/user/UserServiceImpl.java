package com.lyl.service.user;

import java.sql.Connection;
import java.sql.SQLException;

import com.lyl.dao.user.UserDao;
import com.lyl.dao.user.UserDaoImpl;
import com.lyl.entity.User;
import com.lyl.utils.DBUtils;
import com.lyl.utils.SecurityUtils;

public class UserServiceImpl implements UserService {

	@Override
	public boolean add(User user) {
		Connection connection = null;
		Integer count = 0;
		try {
			connection = DBUtils.getConn();
			UserDao userDao = new UserDaoImpl(connection);

			// 密码需要加密
			user.setPassword(SecurityUtils.md5Hex(user.getPassword()));

			count = userDao.add(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(connection, null, null);
			return count > 0;
		}
	}

	@Override
	public boolean update(User user) {
		Connection connection = null;
		Integer count = 0;
		try {
			connection = DBUtils.getConn();
			UserDao userDao = new UserDaoImpl(connection);
			count = userDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(connection, null, null);
			return count > 0;
		}
	}

	@Override
	public User login(String loginName, String password) {
		Connection connection = null;
		User user = null;
		try {
			connection = DBUtils.getConn();
			UserDao userDao = new UserDaoImpl(connection);
			user = userDao.getUser(loginName);
			if (user == null) {
				return null;
			}
			if (!SecurityUtils.md5Hex(password).equals(user.getPassword())) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(connection, null, null);
			return user;
		}
	}

}
