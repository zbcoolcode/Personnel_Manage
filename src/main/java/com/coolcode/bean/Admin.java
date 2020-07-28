package com.coolcode.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Admin
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/27 21:36
 * @Description Admin
 * @Version 1.0
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    private String adminid;
    private String adminname;
    private String adminpassword;
    private String role;

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", adminname='" + adminname + '\'' +
                ", adminpassword='" + adminpassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Admin(String adminid, String adminpassword, String role) {
        this.adminid = adminid;
        this.adminpassword = adminpassword;
        this.role = role;
    }
}
