package com.mapper.Login;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LoginMapper {


    @Select("select id from login_table where login_user=#{username} ")
    public Integer Get_IdByUsername(@Param("username")String username);
    @Select("select id from login_table where login_user=#{username} and login_pas=#{password} ")
    public Integer Get_Id(@Param("username")String username,@Param("password")String password);

    @Select("select iden from login_table where login_user=#{username}")
    public Integer Get_Iden(@Param("username")String username);

    @Insert("insert into login_table(login_user,login_pas,iden) values(#{login_user},#{login_pas},0) ")
    public void Insert(@Param("login_user") String login_user,@Param("login_pas") String login_pas);

    @Update("update login_table set login_pas=#{new_password} where login_user=#{account} ")
    public void Update_Pasword(@Param("new_password") String new_pas,@Param("account")String account);

    @Select("select login_pas from login_table where login_user =#{account}")
    public String Get_Password_ByAccount(@Param("account") String account);


}
