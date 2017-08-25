package com.lang.meeting.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lang.meeting.dao.EmployeeDao;

public class ApproveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ApproveServlet() {
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
		int employeeid = Integer.parseInt(request.getParameter("employeeid"));
		String oper = request.getParameter("oper");
		EmployeeDao dao = new EmployeeDao();
		if (oper!=null&&oper.equals("yes")) {
			dao.updateStatus(employeeid, "1");
			request.getRequestDispatcher("ViewAllEmployeesServlet?code=approve").forward(request, response);
		}
		if(oper!=null&&oper.equals("no")){
			dao.updateStatus(employeeid, "2");
			request.getRequestDispatcher("ViewAllEmployeesServlet?code=approve").forward(request, response);
		}
		if(oper!=null&&oper.equals("close")){
			dao.updateStatus(employeeid, "2");
			request.getRequestDispatcher("SearchEmployeesServlet").forward(request, response);
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
