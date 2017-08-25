package com.lang.meeting.service;

import java.util.List;

import com.lang.meeting.dao.DepartmentDao;
import com.lang.meeting.vo.Department;

public class DepartmentService {
	private DepartmentDao dao = new DepartmentDao();
	
	public List<Department> viewAllDepartments(){
		return dao.selectAll();
	}
	
	public void addDepartment(String departmentname){
		dao.insert(departmentname);
	}
	public void deleteDepartment(int departmentid){
		dao.delete(departmentid);
	}
}