package com.coolcode.service;

import java.util.List;

import com.coolcode.bean.Department;
import com.coolcode.dao.DepartmentDao;
import com.coolcode.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Department> getDeps(){
		List<Department> deps = departmentDao.getDeps();
		return deps;
	}

	public int updateDep(Department department) {
		return departmentDao.updateDep(department);
	}

	public Department getDepById(Integer depid) {
		return departmentDao.getDepById(depid);
	}

	public int addDep(Department department) {
		return departmentDao.addDep(department);
	}

	public int deleteDep(Integer depid) {
		int i = employeeDao.deleteEmpsByDepId(depid);
		int result = departmentDao.deleteDep(depid);
		return result;
	}
}
