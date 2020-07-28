package com.coolcode.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	private Integer depid;
	private String depname;
	private String depmanager;
	private String depaddress;
	private Integer depcount;
}
