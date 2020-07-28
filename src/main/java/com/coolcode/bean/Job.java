package com.coolcode.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	private Integer jobid;
	private String jobname;
	private Integer jobcount;
}

