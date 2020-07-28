package com.coolcode.bean;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/*
 * 员工实体类：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer empid;        //部门编号
    private Department department;    //员工部门
    private String empname;        //员工姓名
    private String gender;        //员工性别
    private Date hiredate;        //入厂时间
    private String address;        //员工住址
    private Date birthdate;        //员工生日
    private String phone;        //联系方式
    private Job job;                //员工职位
    private Integer salary;        //员工薪资
    private String password;    //员工账号登陆密码

}
