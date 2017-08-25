package com.lang.meeting.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lang.meeting.dao.DepartmentDao;
import com.lang.meeting.dao.MeetingRoomDao;
import com.lang.meeting.service.MeetingService;
import com.lang.meeting.vo.Department;
import com.lang.meeting.vo.Meeting;
import com.lang.meeting.vo.MeetingRoom;

public class BookMeetingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public BookMeetingServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code!=null&&code.equals("prepare")) {
			MeetingRoomDao roomDao = new MeetingRoomDao();
			DepartmentDao deptDao = new DepartmentDao();
			
			List<MeetingRoom> roomsList = roomDao.selectAllMeetingRooms();
			List<Department> deptsList = deptDao.selectAll();
			
			request.setAttribute("roomsList", roomsList);
			request.setAttribute("deptsList", deptsList);
			
			request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
		}
		
		if (code!=null&&code.equals("book")) {
			MeetingService meetingService = new MeetingService();
			
			String meetingname = request.getParameter("meetingname");
			int roomid = Integer.parseInt(request.getParameter("roomid"));
			
			HttpSession session = request.getSession();
			int reservationistid = (Integer) session.getAttribute("employeeid");
			
			int numofparticipants = Integer.parseInt(request.getParameter("numofparticipants"));
			Timestamp starttime = Timestamp.valueOf(request.getParameter("starttime"));
			Timestamp endtime = Timestamp.valueOf(request.getParameter("endtime"));
			Timestamp reservationtime = new Timestamp(System.currentTimeMillis());
			Timestamp canceledtime = null;
			String status = "0";
			String description = request.getParameter("description");
			
			Meeting meeting = new Meeting(meetingname, roomid, reservationistid, numofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
			
			String[] employeesid = request.getParameterValues("selSelectedEmployees");
			
			List<Integer> employeesidList = new ArrayList<Integer>();
			
			for (String s : employeesid) {
				employeesidList.add(Integer.parseInt(s));
			}
			
			meetingService.bookMeeting(meeting, employeesidList);
			request.getRequestDispatcher("ViewMyBookingServlet").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
