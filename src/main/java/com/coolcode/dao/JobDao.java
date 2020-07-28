package com.coolcode.dao;

import com.coolcode.bean.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface JobDao {
	
	//查询所有职位
	@Select("select * from jobs")
	@Results({
			@Result(id=true,property = "jobid",column = "job_id"),
			@Result(property = "jobname",column = "job_name"),
			@Result(property = "jobcount",column = "job_count"),
	})
	public List<Job> getJobs();


	//查询所有职位
	@Select("select * from jobs where job_id=#{jobid}")
	@Results({
			@Result(id=true,property = "jobid",column = "job_id"),
			@Result(property = "jobname",column = "job_name"),
			@Result(property = "jobcount",column = "job_count"),
	})
	public Job getJobById(Integer jobid);

	//查询所有职位
	@Insert("insert into jobs(job_name) values (#{jobname})")
	public int addJob(Job job);

	@Update("update jobs set job_name=#{jobname} where job_id=#{jobid}")
	int updateJob(Job job);


	@Delete("delete from jobs where job_id=#{jobid}")
	int deleteJob(Integer jobid);

}
