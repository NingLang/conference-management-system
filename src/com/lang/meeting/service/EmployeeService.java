package com.lang.meeting.service;

import java.util.List;

import com.lang.meeting.dao.EmployeeDao;
import com.lang.meeting.vo.Employee;

public class EmployeeService {
//	service类关联dao类
	private EmployeeDao dao = new EmployeeDao();
/**
 * 	登录成功后的Employee对象
 */
	private Employee logineEmployee;
/**
 * 	页数
 */
	private int countOfPages;
/**
 * 	查询得到的所有记录数量
 */
	private int countOfEmployees;
/**
 * 	每一页记录数
 */
	private int pageSize=3;
//	flag=3,表示用户名密码不正确；flag=1，登陆成功；flag=0，注册待审核；flag=2，审核未通过。
	public int login(String username,String pwd){
		int flag = 3;
		Employee e = dao.selectByNamePwd(username, pwd);
		if(e!=null){
			String status = e.getStatus();
			if(status!=null&&status.equals("1")){
				logineEmployee = e;
				flag = 1;
			}
			if(status!=null&&status.equals("0")){
				flag = 0;
			}
			if(status!=null&&status.equals("2")){
				flag = 2;
			}
		}
		return flag;
	}
	//注册功能，如果账号名存在，注册失败，返回0，否则注册成功返回1。
	public int regist(Employee employee){
		int flag=0;
		Employee e = dao.selectByName(employee.getUsername());
		if (e==null) {
			flag = 1;
			dao.insert(employee);
		}
		return flag;
	}
	
	public Employee getLoginEmployee(){
		return logineEmployee;
	}
	public List<Employee> searchEmployees(String employeename,String username,String status){
		List<Employee> list = dao.selectEmployeesByNameStatus(employeename, username, status);
		countOfEmployees = list.size();
		return list;
	}
//	查询每一页数据集合
	public List<Employee> searchEmployeeOfOnePage(String employeename,String username,String status,int start,int count){
		return dao.selectEmployeesOfOnePage(employeename, username, status, start, count);
	}
//	返回总页数
	public int getCountOfPage(){
		countOfPages=(countOfEmployees%pageSize==0)?countOfEmployees/pageSize:countOfEmployees/pageSize+1;
		return this.countOfPages;
	}
//	返回所有记录条数
	public int getCountOfEmployees(){
		return this.countOfEmployees;
	}
//	返回每页记录数，默认为3
	public int getPageSize(){
		return this.pageSize;
	}
	public static void main(String[] args) {
		EmployeeService service = new EmployeeService();
		int flag = service.login("ninglang", "1");
		System.out.println(flag);
		System.out.println(service.regist(new Employee("李四","lisi1","1",1,"1234567890","li@163.com","0","2")));
	}
}
