package com.lang.meeting.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	private static Connection conn = null;
	/*
	 * 返回一个唯一的数据库连接对象
	 */
//	public static Connection getConnection(){
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting?characterEncoding=utf8&useSSL=false","root","123456");
////			System.out.println("Connection Success!");
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return conn;
//	}
	public static Connection getConnection() {
		System.out.println("Datasource");
		try {
			Context ctx = new InitialContext();
			String strLookup = "java:comp/env/jdbc/meeting";
			DataSource ds = (DataSource)ctx.lookup(strLookup);
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 关闭数据库连接对象
	 */
	public static void closeConnection(){
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
