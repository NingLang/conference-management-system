package com.lang.meeting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lang.meeting.dao.DepartmentDao;
import com.lang.meeting.vo.Department;

public class ViewAllDepartmentsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ViewAllDepartmentsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DepartmentDao dao = new DepartmentDao();
		//查询所有部门信息
		List<Department> departmentslist  = dao.selectAll();
		//把部门信息保存到亲求属性
		request.setAttribute("departmentslist", departmentslist);
		
		String code = request.getParameter("code");
		//跳转到regist.jsp
		if (code!=null&&code.equals("regist")) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		//跳转到departmentsjsp
		if (code!=null&&code.equals("Viewalldepartments")) {
			request.getRequestDispatcher("departments.jsp").forward(request, response);
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
