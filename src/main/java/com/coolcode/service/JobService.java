package com.coolcode.service;

import java.util.List;

import com.coolcode.bean.Department;
import com.coolcode.bean.Job;
import com.coolcode.dao.EmployeeDao;
import com.coolcode.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;

	@Autowired
	private EmployeeDao employeeDao;

	public List<Job> getJobs() {
		return jobDao.getJobs();
	}

	public int addJob(Job job) {
		return jobDao.addJob(job);
	}


	public Job getJobById(Integer jobid){
		return jobDao.getJobById(jobid);
	}

	public int updateJob(Job job) {

		return jobDao.updateJob(job);

	}

	public int deleteJob(Integer jobid) {
		employeeDao.deleteEmpsByJobId(jobid);
		return jobDao.deleteJob(jobid);

	}


}
