package com.coolcode.service;

import java.util.List;

import com.coolcode.bean.Employee;
import com.coolcode.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Cacheable(cacheNames = "emps")
	public List<Employee> getEmps(){
		return employeeDao.getEmps();
	}
	
	public int updateEmp(Employee employee){
		return employeeDao.updateEmp(employee);
	}
	
	public Employee getEmpById(Integer empid){
		return employeeDao.getEmpById(empid);
	}

	public int deleteEmp(Integer empid) {
		return employeeDao.deleteEmp(empid);
	}

	public int addEmp(Employee employee) {
		return employeeDao.addEmp(employee);
	}

	public int idLegal(Integer empid) {
		Employee emp = employeeDao.getEmpById(empid);
		if(emp!=null) {
			return 1;
		}else {
			return 0;
		}
		
	}

	public List<Employee> getEmpsByDepId(Integer depid) {
		return employeeDao.getEmpsByDepId(depid);
	}

	public List<Employee> getEmpsByCondition(Employee employee) {

		List<Employee> emps = employeeDao.getEmpsByCondition(employee);
		return emps;
	}

	public List<Employee> getEmpsByJobId(Integer jobid) {
		List<Employee> emps=employeeDao.getEmpsByJobId(jobid);
		return emps;
	}
}

