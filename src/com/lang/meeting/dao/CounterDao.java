package com.lang.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lang.meeting.util.ConnectionFactory;

public class CounterDao {
	private Connection conn;
//	更新
	public void update(int visitcount){
		conn=ConnectionFactory.getConnection();
		String sql = "update counter set visitcount=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, visitcount);
			pstmt.executeUpdate();
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionFactory.closeConnection();
		}
	}
	
//	查询
	public int select(){
		int visitcount=0;
		conn=ConnectionFactory.getConnection();
		String sql="select * from counter ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				visitcount = rs.getInt("visitcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return visitcount;
	}
	
	
}
