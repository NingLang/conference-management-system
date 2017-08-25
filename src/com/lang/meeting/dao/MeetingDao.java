package com.lang.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lang.meeting.util.ConnectionFactory;
import com.lang.meeting.vo.Meeting;


public class MeetingDao {
	
	private Connection conn;
	
//	向meeting中插入记录，其中status使用默认值0
	public int insert(Meeting meeting){
		int meetingid = 0;
		conn = ConnectionFactory.getConnection();
		String sql = "insert into meeting"
				+"(meetingname,roomid,reservationistid,numberofparticipants,starttime,endtime,reservationtime,canceledtime,description,status)"
				+"values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, meeting.getMeetingname());
			pstmt.setInt(2, meeting.getRoomid());
			pstmt.setInt(3, meeting.getReservationistid());
			pstmt.setInt(4, meeting.getNumberofparticipants());
			pstmt.setTimestamp(5, meeting.getStarttime());
			pstmt.setTimestamp(6, meeting.getEndtime());
			pstmt.setTimestamp(7, meeting.getReservationtime());
			pstmt.setTimestamp(8, meeting.getCanceledtime());
			pstmt.setString(9, meeting.getDescription());
			pstmt.setString(10, "0");
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.executeQuery("select max(meetingid) from meeting");
			if (rs.next()) {
				meetingid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
			return meetingid;
	}
	
//	根据meetingid，更新会议的状态，删除时间
	public void update(int meetingid,String status,Timestamp canceledtime){
		conn = ConnectionFactory.getConnection();
		String sql = "update meeting set status=?,canceledtime=? where meetingid="+meetingid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setTimestamp(2, canceledtime);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
	}
	
//	查询某员工预定的所有会议
	public List<Meeting> selectAllMeetingByReserId(int reservationistid){
		conn = ConnectionFactory.getConnection();
		List<Meeting> meetingsList = new ArrayList<Meeting>();
		Meeting meeting = null;
		try{
			PreparedStatement st = null;
			String sql = "select * from meeting where reservationistid="+reservationistid;
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				meeting = new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setMeetingname(rs.getString("meetingname"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReservationistid(rs.getInt("reservationistid"));
				meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
				meeting.setStarttime(rs.getTimestamp("starttime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setReservationtime(rs.getTimestamp("reservationtime"));
				meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
				meeting.setDescription(rs.getString("description"));
				meeting.setStatus(rs.getString("status"));
				meetingsList.add(meeting);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return meetingsList;
	}
	
	public Meeting selectByid(int meetingid){
		conn = ConnectionFactory.getConnection();
		Meeting meeting = null;
		try{
			PreparedStatement st = null;
			String sql = "select * from meeting where meetingid="+meetingid;
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				meeting = new Meeting();
				meeting.setMeetingid(rs.getInt("meetingid"));
				meeting.setMeetingname(rs.getString("meetingname"));
				meeting.setRoomid(rs.getInt("roomid"));
				meeting.setReservationistid(rs.getInt("reservationistid"));
				meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
				meeting.setStarttime(rs.getTimestamp("starttime"));
				meeting.setEndtime(rs.getTimestamp("endtime"));
				meeting.setReservationtime(rs.getTimestamp("reservationtime"));
				meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
				meeting.setDescription(rs.getString("description"));
				meeting.setStatus(rs.getString("status"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return meeting;
	}
	public static void main(String[] args) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
