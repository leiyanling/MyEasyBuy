package com.lyl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyl.entity.News;
import com.lyl.utils.DBUtils;

public class BaseDao {

	private Connection conn;
	public PreparedStatement ps;

	public BaseDao(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public int execute(String sql, Object... params) throws SQLException {// ...
																			// 当成数组使用
		Connection conn = getConn();
		PreparedStatement ps = null;

		ps = conn.prepareStatement(sql);
		// 参数的个数 也是不确定的... 多个参数, 什么类型...
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
		int num = ps.executeUpdate();// 添删改通用的方法
		DBUtils.closeAll(null, ps, null);// 不能关闭conn 把conn交给了逻辑业务从

		return num;

		// 操作失败
	}

	// 查询
	public ResultSet executeQuery(String sql, Object[] params) throws SQLException {// ...
		Connection conn = getConn();

		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常了");
		}

		return null;

	}
}
