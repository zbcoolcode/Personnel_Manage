package com.coolcode.controller;

import java.util.List;

import com.coolcode.bean.Job;
import com.coolcode.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@RequestMapping("/jobs")
	public String getJobs(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model) {
		PageHelper.startPage(pn,8);
		List<Job> jobs = jobService.getJobs();
		PageInfo<Job> jobpageInfo = new PageInfo<Job>(jobs);
		model.addAttribute("jobpageInfo", jobpageInfo);
		return "job-list";
	}

	@RequestMapping("/job")
	public String getJobById(Integer jobid,Model model) {
		Job job = jobService.getJobById(jobid);
		System.out.println(job);
		model.addAttribute("job", job);
		return "job-update";
	}

	@RequestMapping("/addpage")
	public String addpage() {
		return "job-add";
	}

	@RequestMapping("/add")
	public String addpage(Job job) {
		int i = jobService.addJob(job);
		return "redirect:/job/jobs";
	}


	@RequestMapping("/update")
	public String update(Job job) {
		int i = jobService.updateJob(job);
		return "redirect:/job/jobs";
	}

	@RequestMapping("/delete")
	public String delete(Integer jobid) {
		int i = jobService.deleteJob(jobid);
		System.out.println(i);
		return "redirect:/job/jobs";
	}





}
