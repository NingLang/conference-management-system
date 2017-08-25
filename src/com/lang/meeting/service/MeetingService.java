package com.lang.meeting.service;

import java.sql.Timestamp;
import java.util.List;

import com.lang.meeting.dao.MeetingDao;
import com.lang.meeting.dao.MeetingParticipantsDao;
import com.lang.meeting.vo.Meeting;

public class MeetingService {
	private MeetingDao meetingDao = new MeetingDao();
	private MeetingParticipantsDao parDao = new MeetingParticipantsDao();
	
	public void bookMeeting(Meeting meeting,List<Integer> employeeidList) {
		int meetingid = meetingDao.insert(meeting);
		for (Integer employeeid : employeeidList) {
			parDao.insert(meetingid, employeeid);
		}
	}
	
	public void cancelMeeting(int meetingid){
		meetingDao.update(meetingid, "1", new Timestamp(System.currentTimeMillis()));
	}
	
	public List<Meeting> viewMyBookingInfo(int reservationistid){
		return meetingDao.selectAllMeetingByReserId(reservationistid);
	}
	
	public List<Meeting> viewMyMeetingsInfo(int participantsid) {
		return parDao.selectAllMeetingsByParId(participantsid);
	}
}
