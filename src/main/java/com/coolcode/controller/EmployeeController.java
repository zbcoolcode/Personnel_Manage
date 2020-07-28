package com.coolcode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.coolcode.bean.Department;
import com.coolcode.bean.Employee;
import com.coolcode.bean.Job;
import com.coolcode.service.DepartmentService;
import com.coolcode.service.EmployeeService;
import com.coolcode.service.JobService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobService JobService;

    @RequestMapping("/emps")
    public ModelAndView getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(pn, 8);
        List<Employee> emps = employeeService.getEmps();
        List<Department> deps = departmentService.getDeps();
        List<Job> jobs = JobService.getJobs();
        PageInfo<Employee> emppageInfo = new PageInfo<Employee>(emps);
        mv.addObject("deps", deps);
        mv.addObject("jobs", jobs);
        mv.addObject("emppageInfo", emppageInfo);
        mv.setViewName("emp-list");
        return mv;
    }

    @RequestMapping("/empsbycondition")
    public String getEmpsByCondition(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                     Model model,
                                     Integer depid,
                                     Integer jobid) {
        PageHelper.startPage(pn, 8);
        ModelAndView mv = new ModelAndView();
        List<Employee> emps = null;
        Employee employee = new Employee();
        if (jobid != null && depid != null) {
            Job job = new Job();
            job.setJobid(jobid);
            employee.setJob(job);
            Department dep = new Department();
            dep.setDepid(depid);
            employee.setDepartment(dep);
            emps = employeeService.getEmpsByCondition(employee);
        }
        if (depid != null && jobid == null) {
            emps = employeeService.getEmpsByDepId(depid);
        }

		if (jobid != null && depid == null) {
			emps = employeeService.getEmpsByJobId(jobid);
		}

        if (depid == null && jobid == null) {
            return "redirect:/emp/emps";
        }
		System.out.println("奥里给"+emps);
        PageInfo<Employee> emppageInfo = new PageInfo<Employee>(emps);
        System.out.println(emps);
        List<Department> deps = departmentService.getDeps();
        List<Job> jobs = JobService.getJobs();

        model.addAttribute("deps", deps);
        model.addAttribute("jobs", jobs);
        model.addAttribute("emppageInfo", emppageInfo);
        return "emp-list";
    }


    @RequestMapping(value = "/addpage", method = RequestMethod.GET)
    public ModelAndView addpage() {
        ModelAndView mv = new ModelAndView();
        List<Department> deps = departmentService.getDeps();
        List<Job> jobs = JobService.getJobs();
        mv.addObject("deps", deps);
        mv.addObject("jobs", jobs);
        System.out.println(deps);
        System.out.println(jobs);
        mv.setViewName("emp-add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Employee employee) {
        System.out.println(employee);
        int i = employeeService.addEmp(employee);
        System.out.println(i);
        return "redirect:/emp/emps";
    }

    //通过员工ID得到员工
    @ResponseBody
    @RequestMapping("/emp")
    public ModelAndView getEmpById(Integer empid) {
        System.out.println(empid);
        ModelAndView mv = new ModelAndView();
        Employee empById = employeeService.getEmpById(empid);
        System.out.println(empById);
        List<Department> deps = departmentService.getDeps();
        List<Job> jobs = JobService.getJobs();
        mv.addObject("deps", deps);
        mv.addObject("jobs", jobs);
        mv.addObject("emp", empById);
        mv.setViewName("emp-update");
        return mv;
    }


    // 跳转到更新页面，先要查询到所要更新的员工的信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Employee employee) {
        System.out.println(employee);
        int i = employeeService.updateEmp(employee);
        System.out.println(i);
        return "redirect:/emp/emps";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteEmp(@RequestParam("empid") Integer empid) {
        System.out.println(empid);
        int i = employeeService.deleteEmp(empid);
        System.out.println(i);
        return "redirect:/emp/emps";
    }


    //添加员工时判断empid是否存在
    @ResponseBody
    @RequestMapping(value = "/islegal", method = RequestMethod.POST)
    public Map<String, Object> empid_legal(Integer empid) {
        int count = employeeService.idLegal(empid);
        boolean isExist = (count > 0 ? true : false);
        if (isExist) {
            System.out.println(empid + "号员工已经存在");
        } else {
            System.out.println(empid + "可以作为员工号");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("isExist", isExist);
        return resultMap;
    }
}
	 

