package com.lang.meeting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lang.meeting.service.EmployeeService;
import com.lang.meeting.vo.Employee;

public class SearchEmployeesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SearchEmployeesServlet() {
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
		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		EmployeeService service = new EmployeeService();
//		当前页码
		String PageNumStr = request.getParameter("pageNum");
		int pageNum=0;
		if (PageNumStr==null||PageNumStr.equals("")) {
			pageNum=1;
		}else {
			pageNum=Integer.parseInt(PageNumStr);
		}
//		每页的记录数量
		int pageSize = service.getPageSize();
//		起始记录索引
		int start = (pageNum-1)*pageSize;
//		查询的数量
		int count = pageSize;
//		获得所有记录数量，先调用DAO中的Search方法
		service.searchEmployees(employeename, username, status);
		int countOfEmployees = service.getCountOfEmployees();
//		页数
		int countOfPages = service.getCountOfPage();
		
		
		List<Employee> list = service.searchEmployeeOfOnePage(employeename, username, status, start, count);
		request.setAttribute("employeeslist", list);
//		使用search标记调用了SearchEmployeesServlet，即显示结果表格
		request.setAttribute("search", "1");
//		存储页数，所有记录的数量，当前页码
		request.setAttribute("countOfPages", countOfPages);
		request.setAttribute("countOfEmployees", countOfEmployees);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("searchemployees.jsp").forward(request, response);
		
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
