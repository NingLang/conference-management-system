package com.lang.meeting.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lang.meeting.dao.DepartmentDao;
import com.lang.meeting.service.EmployeeService;
import com.lang.meeting.vo.Department;
import com.lang.meeting.vo.Employee;

public class RegistServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RegistServlet() {
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
//		使用过滤器之后，不用设置编码
//		request.setCharacterEncoding("utf-8");
//		获取注册页面请求参数
		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		Employee employee = new Employee(employeename, username, password, deptid, email, phone, "0", "2");
		EmployeeService service = new EmployeeService();
		int flag = service.regist(employee);
		if (flag==1) {
			request.setAttribute("msg","注册成功，正在审核。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "用户名已存在，请重新注册");
			//重新查询所有部门，保证能返回到JSP页面。
			DepartmentDao dao = new DepartmentDao();
			List<Department> departmentsList = dao.selectAll();
			request.setAttribute("departmentslist", departmentsList);
			request.getRequestDispatcher("register.jsp").forward(request, response);
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
