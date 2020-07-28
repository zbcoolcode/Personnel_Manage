package com.coolcode.dao;

import com.coolcode.bean.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @ClassName AdminDao
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/27 21:38
 * @Description AdminDao
 * @Version 1.0
 */
public interface AdminDao {

    @Select("select * from admin")
    @Results({
            @Result(id=true,property = "adminid",column = "id"),
            @Result(property = "adminname",column = "name"),
            @Result(property = "adminpassword",column = "password"),
            @Result(property = "role",column = "role"),
    })
    public List<Admin> getAdmins();


    @Select("select * from admin where id=#{adminid}")
    @Results({
            @Result(id=true,property = "adminid",column = "id"),
            @Result(property = "adminname",column = "name"),
            @Result(property = "adminpassword",column = "password"),
            @Result(property = "role",column = "role"),
    })
    public Admin getAdminById(String adminid);

    @Update(("update admin set name=#{adminname},password=#{adminpassword},role=#{role} where id=#{adminid}"))
    int updateAdmin(Admin admin);

    @Delete("delete from admin where id=#{adminid}")
    int deleteAdmin(Integer adminid);

    @Insert("insert into admin(id,name,password,role) values(#{adminid},#{adminname},#{adminpassword},#{role})")
    int addAdmin(Admin admin);
}
