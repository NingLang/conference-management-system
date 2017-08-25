package com.lang.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lang.meeting.dao.EmployeeDao;
import com.lang.meeting.vo.Employee;

public class SelectEmployeesOfDeptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SelectEmployeesOfDeptServlet() {
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
		int departmentid = Integer.parseInt(request.getParameter("departmentid"));
		
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employeesList = dao.selectEmployeesByDept(departmentid);
		
//		将查询到的员工信息，以XML围挡的格式返回到浏览器
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		//<?xml version="1.0" encoding="UTF-8"?>
		out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//		符合XML规范，有根节点，否则解析有问题
		out.println("<employees>");
		for (Employee employee : employeesList) {
			out.println("<option>");
			out.println("<value>"+employee.getEmployeeid()+"</value>");
			out.println("<text>"+employee.getEmployeename()+"</text>");
			out.println("</option>");
		}
		out.println("</employees>");
		out.close();
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
