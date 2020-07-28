package com.coolcode.dao;

import com.coolcode.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;



public interface DepartmentDao {
	
	//查询所有部门
	@Select("select * from departments")
	@Results({
			@Result(id=true,property = "depid",column = "department_id"),
			@Result(property = "depname",column = "department_name"),
			@Result(property = "depmanager",column = "department_manager"),
			@Result(property = "depaddress",column = "department_address"),
			@Result(property = "depcount",column = "department_count"),
	})
	public List<Department> getDeps();
	
	//修改部门信息
	@Update("update departments set department_name=#{depname},department_manager=#{depmanager},department_address=#{depaddress} where department_id=#{depid}")
	public int updateDep(Department department);

	//通过部门ID查询部门信息
	@Select("select * from departments where department_id=#{depid}")
	@Results({
			@Result(id=true,property = "depid",column = "department_id"),
			@Result(property = "depname",column = "department_name"),
			@Result(property = "depmanager",column = "department_manager"),
			@Result(property = "depaddress",column = "department_address"),
			@Result(property = "depcount",column = "department_count"),
	})
	public Department getDepById(Integer depid);

	//添加部门
	@Insert("insert into departments(department_name,department_manager,department_address) value(#{depname},#{depmanager},#{depaddress})")
	public int addDep(Department department);

	//删除部门
	@Delete("delete from departments where department_id=#{depid}")
	public int deleteDep(Integer depid);

}
