package com.lang.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lang.meeting.util.ConnectionFactory;
import com.lang.meeting.vo.Employee;

public class EmployeeDao {
	private Connection conn = ConnectionFactory.getConnection();
	
	public Employee selectByNamePwd(String username, String pwd) {
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from employee where username='"+username+"' and password='"+pwd+"'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()==true) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employee;
		
	}
	
	public Employee selectByName(String username) {
		conn = ConnectionFactory.getConnection();
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from employee where username='"+username+"'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()==true) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employee;
	}
	public Employee selectById(int employeeId) {
		conn = ConnectionFactory.getConnection();
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from employee where employeeid='"+employeeId+"'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()==true) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employee;
	}
	
	public List<Employee> selectAllemployee() {
		conn = ConnectionFactory.getConnection();
		List<Employee> employeeslist = new ArrayList<Employee>();
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = "select * from employee where role='2' and status='0'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()==true) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employeeslist.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employeeslist;
		
	}
	
//	根据姓名、用户名、状态、查询所有员工信息，返回到集合中。
	public List<Employee> selectEmployeesByNameStatus(String employeename,String username,String status){
		conn = ConnectionFactory.getConnection();
		
		List<Employee> employeeslist = new ArrayList<>();
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = null;
			String usernamesql,employeenamesql,statussql;
			
			if (employeename == null||employeename.equals("")) {
				employeenamesql="";
			}else {
				employeenamesql=" and employeename='"+employeename+"'";
			}
			if (username == null||username.equals("")) {
				usernamesql="";
			}else {
				usernamesql=" and username='"+username+"'";
			}
			if (status == null||status.equals("")) {
				statussql="";
			}else {
				statussql=" and status='"+status+"'";
			}
			sql="select * from Employee where role='2'"+usernamesql+employeenamesql+statussql;
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employeeslist.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employeeslist;
	}
	
	public List<Employee> selectEmployeesOfOnePage(String employeename,String username,String status,int start,int count){
		conn = ConnectionFactory.getConnection();
		
		List<Employee> employeeslist = new ArrayList<>();
		Employee employee = null;
		try {
			PreparedStatement st = null;
			String sql = null;
			String usernamesql,employeenamesql,statussql;
			
			if (employeename == null||employeename.equals("")) {
				employeenamesql="";
			}else {
				employeenamesql=" and employeename='"+employeename+"'";
			}
			if (username == null||username.equals("")) {
				usernamesql="";
			}else {
				usernamesql=" and username='"+username+"'";
			}
			if (status == null||status.equals("")) {
				statussql="";
			}else {
				statussql=" and status='"+status+"'";
			}
			sql="select * from Employee where role='2'"+usernamesql+employeenamesql+statussql+" limit "+start+","+count;
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employeeslist.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employeeslist;
	}
	
	public List<Employee> selectEmployeesByDept(int departmentid) {
		conn = ConnectionFactory.getConnection();
		List<Employee> employeeslist = new ArrayList<>();
		Employee employee = null;
		try {
			PreparedStatement pstmt = null;
			String sql = "select * from employee where departmentid="+departmentid;
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				employee = new Employee();
				employee.setEmployeeid(rs.getInt("employeeid"));
				employee.setEmployeename(rs.getString("employeename"));
				employee.setUsername(rs.getString("username"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setStatus(rs.getString("status"));
				employee.setDepartmentid(rs.getInt("departmentid"));
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				employeeslist.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		return employeeslist;
	}
	
	public void updateStatus(int employeeid,String status){
		conn = ConnectionFactory.getConnection();
		String sql = "update employee set status='"+status+"' where employeeid="+employeeid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		
	}
	public void insert(Employee employee) {
		conn = ConnectionFactory.getConnection();
		String sql = "insert into employee"
				+ "(employeename,username,password,phone,email,departmentid,status,role)"
				+" value(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employee.getEmployeename());
			pstmt.setString(2, employee.getUsername());
			pstmt.setString(3, employee.getPassword());
			pstmt.setString(4, employee.getPhone());
			pstmt.setString(5, employee.getEmail());
			pstmt.setInt(6, employee.getDepartmentid());
			//注册成功后，默认为正在审核，stuts为0
			pstmt.setString(7, "0");
			//新注册员工默认为普通员工，role为2
			pstmt.setString(8, "2");
			pstmt.executeUpdate();
			System.out.println("插入成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
//		Employee e = dao.selectByNamePwd("ninglang", "1");
//		if (e!=null) {
//			System.out.println(e);
//		}else {
//			System.out.println("登录失败");
//		}
//		Employee e = dao.selectByName("ninglang");
//		System.out.println(e);
		
//		dao.insert(new Employee("李四","lisi","1",1,"1234567890","li@163.com","0","2"));
		List<Employee> list = dao.selectEmployeesByNameStatus(null, "admin", null);
		for(Employee e:list){
			System.out.println(e);
		}
	}
}
