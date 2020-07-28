package com.coolcode.controller;

import java.util.List;

import com.coolcode.bean.Department;
import com.coolcode.bean.Employee;
import com.coolcode.service.DepartmentService;
import com.coolcode.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.ModelAndView;
import sun.java2d.pipe.SpanIterator;


@Controller
@RequestMapping("/dep")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/deps")
	public ModelAndView getDeps(@RequestParam(value = "pn",defaultValue = "1")Integer pn) {
		ModelAndView mv = new ModelAndView();
		PageHelper.startPage(pn, 6);
		List<Department> deps = departmentService.getDeps();
		System.out.println(deps);
		PageInfo<Department> deppageInfo=new PageInfo<Department>(deps);
		mv.addObject("deppageInfo", deppageInfo);
		mv.setViewName("dep-list");
		return mv;
	}
	
	
	//转到添加的界面
	@RequestMapping("/addpage")
	public ModelAndView addpage() {
		return new ModelAndView("dep-add");
	}


	//转到修改的界面
	//查询单个部门
	@RequestMapping("/dep")
	public ModelAndView updatepage(@RequestParam("depid")Integer depid) {
		ModelAndView mv = new ModelAndView();
		Department dep = departmentService.getDepById(depid);
		mv.addObject("dep",dep);
		mv.setViewName("dep-update");
		return mv;
	}
	
	//更新部门信息
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(Department department) {
		System.out.println(department);
		int updateEmp=departmentService.updateDep(department);
		return "redirect:/dep/deps";
	}
	

	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String addDep(Department department) {
		int addemp=departmentService.addDep(department);
		return "redirect:/dep/deps";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteDep(@RequestParam("depid")Integer depid) {
		int i=departmentService.deleteDep(depid);
		return "redirect:/dep/deps";
	}
	
	//通过部门ID查询该部门的所有员工
	@ResponseBody
	@RequestMapping(value = "/empsbydepid")
	public List<Employee> getEmpsByDepId(@RequestParam("depid")Integer depid) {
		List<Employee> emps=employeeService.getEmpsByDepId(depid);
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		return emps;
	}
}
