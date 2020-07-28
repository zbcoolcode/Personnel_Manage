package com.coolcode.dao;

import com.coolcode.bean.Department;
import com.coolcode.bean.Employee;
import com.coolcode.bean.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;



public interface EmployeeDao {
	
	//查询所有员工
	@Select("select * from employees")
	@Results({
			@Result(id=true,property = "empid",column = "employee_id"),
			@Result(property = "empname",column = "name"),
			@Result(property = "gender",column = "gender"),
			@Result(property = "hiredate",column = "hiredate"),
			@Result(property = "address",column = "address"),
			@Result(property = "phone",column = "phone"),
			@Result(property = "salary",column = "salary"),
			@Result(property = "password",column = "password"),
			@Result(property = "department",column = "department_id",javaType = Department.class,one = @One(select = "com.coolcode.dao.DepartmentDao.getDepById")),
			@Result(property = "job",column = "job_id",javaType = Job.class,one = @One(select = "com.coolcode.dao.JobDao.getJobById")),
	})
	public List<Employee> getEmps();
	
	//修改员工信息
	@Update("update employees set department_id=#{department.depid},address=#{address},birthdate=#{birthdate},phone=#{phone},job_id=#{job.jobid},salary=#{salary},password=#{password} where employee_id=#{empid}")
	public int updateEmp(Employee employee);

	//得到单个员工的信息
	@Select("select * from employees where employee_id=#{empid}")
	@Results({
			@Result(id=true,property = "empid",column = "employee_id"),
			@Result(property = "empname",column = "name"),
			@Result(property = "gender",column = "gender"),
			@Result(property = "hiredate",column = "hiredate"),
			@Result(property = "address",column = "address"),
			@Result(property = "phone",column = "phone"),
			@Result(property = "salary",column = "salary"),
			@Result(property = "password",column = "password"),
			@Result(property = "department",column = "department_id",javaType = Department.class,one = @One(select = "com.coolcode.dao.DepartmentDao.getDepById")),
			@Result(property = "job",column = "job_id",javaType = Job.class,one = @One(select = "com.coolcode.dao.JobDao.getJobById")),
	})
	public Employee getEmpById(Integer empid);

	//删除单个员工
	@Delete("delete from employees where employee_id=#{empid}")
	public int deleteEmp(Integer empid);

	//添加员工
	@Insert("insert into employees values(#{department.depid},#{empid},#{empname},#{gender},#{hiredate},#{address},#{birthdate},#{phone},#{job.jobid},#{salary},#{password}) ")
	public int addEmp(Employee employee);

	//通过部门ID查询该部门的所有员工
	@Select("select *from employees where department_id=#{depid}")
	@Results({
			@Result(id=true,property = "empid",column = "employee_id"),
			@Result(property = "empname",column = "name"),
			@Result(property = "gender",column = "gender"),
			@Result(property = "hiredate",column = "hiredate"),
			@Result(property = "address",column = "address"),
			@Result(property = "phone",column = "phone"),
			@Result(property = "salary",column = "salary"),
			@Result(property = "password",column = "password"),
			@Result(property = "department",column = "department_id",javaType = Department.class,one = @One(select = "com.coolcode.dao.DepartmentDao.getDepById")),
			@Result(property = "job",column = "job_id",javaType = Job.class,one = @One(select = "com.coolcode.dao.JobDao.getJobById")),
	})
	public List<Employee> getEmpsByDepId(Integer depid);

	//提供部门ID删除员工
	@Delete("delete from employees where department_id=#{depid}")
	public int deleteEmpsByDepId(Integer depid);

	//通过条件及部门或职位查询员工
	@Select("select * from employees where department_id=#{department.depid} and job_id=#{job.jobid}")
	@Results({
			@Result(id=true,property = "empid",column = "employee_id"),
			@Result(property = "empname",column = "name"),
			@Result(property = "gender",column = "gender"),
			@Result(property = "hiredate",column = "hiredate"),
			@Result(property = "address",column = "address"),
			@Result(property = "phone",column = "phone"),
			@Result(property = "salary",column = "salary"),
			@Result(property = "password",column = "password"),
			@Result(property = "department",column = "department_id",javaType = Department.class,one = @One(select = "com.coolcode.dao.DepartmentDao.getDepById")),
			@Result(property = "job",column = "job_id",javaType = Job.class,one = @One(select = "com.coolcode.dao.JobDao.getJobById")),
	})
	List<Employee> getEmpsByCondition(Employee employee);

	//提供职位ID删除员工
	@Delete("delete from employees where job_id=#{jobid}")
	void deleteEmpsByJobId(Integer jobid);

	//通过部门ID查询该部门的所有员工
	@Select("select *from employees where job_id=#{jobid}")
	@Results({
			@Result(id=true,property = "empid",column = "employee_id"),
			@Result(property = "empname",column = "name"),
			@Result(property = "gender",column = "gender"),
			@Result(property = "hiredate",column = "hiredate"),
			@Result(property = "address",column = "address"),
			@Result(property = "phone",column = "phone"),
			@Result(property = "salary",column = "salary"),
			@Result(property = "password",column = "password"),
			@Result(property = "department",column = "department_id",javaType = Department.class,one = @One(select = "com.coolcode.dao.DepartmentDao.getDepById")),
			@Result(property = "job",column = "job_id",javaType = Job.class,one = @One(select = "com.coolcode.dao.JobDao.getJobById")),
	})
	List<Employee> getEmpsByJobId(Integer jobid);
}
