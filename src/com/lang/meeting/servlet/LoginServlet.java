package com.lang.meeting.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lang.meeting.service.EmployeeService;
import com.lang.meeting.vo.Employee;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
		//从jsp中获取用户名密码
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
//		获得时间参数
		String timelength =request.getParameter("timelength");
		int days=0;
		if (timelength!=null) {
			days=Integer.parseInt(timelength);
		}
		if (days!=0) {
//			将用户名和密码，作为cookie对象，进行保存
			Cookie usernamecookie = new Cookie("username", username);
			Cookie passwordcookie = new Cookie("password", pwd);
			usernamecookie.setMaxAge(days*24*3600);
			passwordcookie.setMaxAge(days*24*3600);
			response.addCookie(usernamecookie);
			response.addCookie(passwordcookie);
		}
		
		//调用业务逻辑login方法 
		EmployeeService service = new EmployeeService();
		int flag = service.login(username, pwd);
		
		if(flag==1){
//			获得上下文对象
			ServletContext ctxt = this.getServletContext();
//			判断上下文对象中是否存在visitcount，不存在初始化为0，存在则取出使用。
			int visitcount;
			if(ctxt.getAttribute("visitcount")==null){
				visitcount = 0;
			}else {
				visitcount=Integer.parseInt(ctxt.getAttribute("visitcount").toString());
			}
//			visitcount自增1，并保存到上下文中
			visitcount++;
			ctxt.setAttribute("visitcount", visitcount);
			
//			获得会话对象
			HttpSession session = request.getSession();
			Employee loginedEmployee = service.getLoginEmployee();
			session.setAttribute("employeename", service.getLoginEmployee().getEmployeename());
			session.setAttribute("employeeid", service.getLoginEmployee().getEmployeeid());
			String role = loginedEmployee.getRole();
			if (role.equals("1")) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			if (role.equals("2")){
				request.getRequestDispatcher("employeeindex.jsp").forward(request, response);
			}
		}else {
			if (flag==0) {
				request.setAttribute("msg", "正在审核，请耐心等待。");
			}
			if (flag==2) {
				request.setAttribute("msg", "审核未通过，请核实后重新登录。");
			}
			if (flag==3) {
				request.setAttribute("msg", "用户名或密码错误，请重试");
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
