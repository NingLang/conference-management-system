package com.lang.meeting.service;

import java.util.List;

import com.lang.meeting.dao.MeetingRoomDao;
import com.lang.meeting.vo.MeetingRoom;

public class MeetingRoomService {
	private MeetingRoomDao dao = new MeetingRoomDao();
	
	public List<MeetingRoom> viewAllMeetingRooms(){
		return dao.selectAllMeetingRooms();
	}
	
	public MeetingRoom viewOneMeetingRoom(int roomid){
		return dao.selectByRoomid(roomid);
	}
	
	public void addMeetingRoom(MeetingRoom meetingRoom){
		dao.insert(meetingRoom);
	}
	
	public void updareMeetingRoom(MeetingRoom meetingRoom){
		dao.updateMeetingroom(meetingRoom);
	}
}
