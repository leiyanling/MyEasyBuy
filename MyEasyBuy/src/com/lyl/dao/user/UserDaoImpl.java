package com.lyl.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyl.dao.BaseDao;
import com.lyl.entity.User;
import com.lyl.utils.DBUtils;
import com.lyl.utils.EmptyUtils;

/**
 * 用户dao
 */
public class UserDaoImpl extends BaseDao implements UserDao {

	public UserDaoImpl(Connection connection) {
		super(connection);
	}

	public User tableToClass(ResultSet rs) throws Exception {
		User user = new User();
		user.setLoginName(rs.getString("loginName"));
		user.setUserName(rs.getString("userName"));
		user.setPassword(rs.getString("password"));
		user.setSex(rs.getInt("sex"));
		user.setIdentityCode(rs.getString("identityCode"));
		user.setEmail(rs.getString("email"));
		user.setMobile(rs.getString("mobile"));
		user.setType(rs.getInt("type"));
		user.setId(rs.getInt("id"));
		return user;
	}

	/**
	 * 保存用户
	 *
	 * @param user
	 * @throws java.sql.SQLException
	 */
	public int add(User user) {// 新增用户信息
		Integer id = 0;
		try {
			String sql = " INSERT into easybuy_user(loginName,userName,password,sex,identityCode,email,mobile) values(?,?,?,?,?,?,?) ";
			try {
				Object param[] = new Object[] { user.getLoginName(), user.getUserName(), user.getPassword(),
						user.getSex(), user.getIdentityCode(), user.getEmail(), user.getMobile() };
				id = this.execute(sql, param);
				user.setId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, ps, null);
		}
		return id;
	}

	// 更新用户信息
	public int update(User user) {
		Integer count = 0;
		try {
			Object[] params = new Object[] { user.getLoginName(), user.getUserName(), user.getType(), user.getSex(),
					user.getIdentityCode(), user.getEmail(), user.getMobile(), user.getId() };
			String sql = " UPDATE easybuy_user SET loginName=?,userName =?,type=?,sex =?, identityCode =?, email =?, mobile =? WHERE id =?  ";
			count = this.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, ps, null);
			return count;
		}
	}

	@Override
	public User getUser(String loginName) throws SQLException {
		List<Object> paramsList = new ArrayList<Object>();
		List<User> userList = new ArrayList<User>();
		StringBuffer sql = new StringBuffer(
				"  select id,loginName,userName,password,sex,identityCode,email,mobile,type from easybuy_user where 1=1  ");

		if (EmptyUtils.isNotEmpty(loginName)) {
			sql.append(" and loginName=? ");
			paramsList.add(loginName);
		}

		ResultSet resultSet = this.executeQuery(sql.toString(), paramsList.toArray());
		User user = null;
		try {
			while (resultSet.next()) {
				user = this.tableToClass(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, ps, resultSet);
		}
		return user;
	}

}
