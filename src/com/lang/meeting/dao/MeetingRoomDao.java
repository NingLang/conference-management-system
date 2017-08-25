package com.lang.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lang.meeting.util.ConnectionFactory;
import com.lang.meeting.vo.MeetingRoom;

public class MeetingRoomDao {
//	dao类关联连接工厂类
	private Connection conn;
/**
 * 	添加一个会议室，status默认为0，表示未被占用
 * @param meetingRoom
 */
	public void insert(MeetingRoom meetingRoom){
		conn = ConnectionFactory.getConnection();
		String sql = "insert into meetingroom"
				+"(roomnum,roomname,capacity,status,description)"
				+"values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingRoom.getRoomnum());
			pstmt.setString(2, meetingRoom.getRoomname());
			pstmt.setInt(3, meetingRoom.getCapacity());
			pstmt.setString(4, meetingRoom.getStatus());
			pstmt.setString(5, meetingRoom.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
	}
	
/**
 * 	根据id查询会议室
 * @param roomid
 * @return
 */
	public MeetingRoom selectByRoomid(int roomid){
		conn = ConnectionFactory.getConnection();
		MeetingRoom meetingRoom = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from meetingroom where roomid="+roomid+"";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()==true) {
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingRoom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingRoom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingRoom.setRoomname(rs.getString("roomname"));
				meetingRoom.setStatus(rs.getString("status"));
				meetingRoom.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return meetingRoom;
	}
/**
 * 	查询所有会议室
 * @return
 */
	public List<MeetingRoom> selectAllMeetingRooms(){
		conn = ConnectionFactory.getConnection();
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		MeetingRoom meetingRoom = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from meetingroom";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingRoom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingRoom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingRoom.setRoomname(rs.getString("roomname"));
				meetingRoom.setStatus(rs.getString("status"));
				meetingRoom.setDescription(rs.getString("description"));
				list.add(meetingRoom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return list;
	}
/**
 * 通过时间来查询会议室
 * @param starttime
 * @param endtime
 * @return
 */
	public List<MeetingRoom> selectMeetingRoomsByTime(Timestamp starttime,Timestamp endtime) {
		conn = ConnectionFactory.getConnection();
		List<MeetingRoom> list = new ArrayList<>();
		String start = starttime.toString();
		String end = endtime.toString();
		MeetingRoom meetingRoom = null;
		try {
			PreparedStatement pstmt = null;
			
			String sql = "select * from meetingroom"
					+" where meetingroom.roomid not in"
					+" (select roomid from meeting"
					+" where (starttime<'"+start+"' and endtime>'"+end+"')"
					+" or (starttime>'"+start+"' and starttime <'"+end+"')"
					+" or(endtime>'"+start+"' and endtime <'"+end+"') and status='0')";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				meetingRoom = new MeetingRoom();
				meetingRoom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingRoom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingRoom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingRoom.setRoomname(rs.getString("roomname"));
				meetingRoom.setStatus(rs.getString("status"));
				meetingRoom.setDescription(rs.getString("description"));
				list.add(meetingRoom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return list;
	}
/**
 * 根据id更新会议室
 * @param meetingRoom
 */
	public void updateMeetingroom(MeetingRoom meetingRoom){
		conn = ConnectionFactory.getConnection();
		String sql = "update meetingroom set roomnum=?,roomname=?,capacity=?,status=?,description=? where roomid="+meetingRoom.getRoomid();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingRoom.getRoomnum());
			pstmt.setString(2, meetingRoom.getRoomname());
			pstmt.setInt(3, meetingRoom.getCapacity());
			pstmt.setString(4, meetingRoom.getStatus());
			pstmt.setString(5, meetingRoom.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
	}
	
/**
 * 根据id更新会议室状态
 * @param roomid
 * @param status
 */
	public void updateMeetingRoomStatus(int roomid,String status){
		conn = ConnectionFactory.getConnection();
		String sql = "update meetingroom set status=? where roomid="+roomid;
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
	}
	
	public static void main(String[] args) {
		MeetingRoomDao dao = new MeetingRoomDao();
		MeetingRoom meetingRoom = new MeetingRoom(3301,120,"第XXX会议室","1","cheduzi");
		dao.insert(meetingRoom);
		List<MeetingRoom> list=dao.selectAllMeetingRooms();
		for (MeetingRoom meetingRoom2 : list) {
			System.out.println(meetingRoom2);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
