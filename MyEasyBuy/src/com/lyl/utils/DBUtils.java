package com.lyl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {

	static Context context;
	static DataSource dataSource;
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String dataBaseUrl="jdbc:mysql://localhost:3306/easybuy";
	private static String password="root";
	private static String userName="root";
	
	
	
	

	static {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myEeasbuy");// jdbc/news
//
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}

	public static Connection getConn() {

		try {

			// 代表数据源(连接池)
			//return dataSource.getConnection();

			return DriverManager.getConnection(dataBaseUrl, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
