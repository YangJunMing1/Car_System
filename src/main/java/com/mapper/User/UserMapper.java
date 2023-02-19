package com.mapper.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pojo.User;

import java.util.ArrayList;

/**
 * 内含user_table的改动函数
 */
public interface UserMapper {

    @Select("select * from user_table")
    ArrayList<User> Select_All();

    @Select("select * from user_table where id = #{id}")
    User Select_ById(int id);

    @Select("select * from user_table where phone = #{phone}")
    User Select_Byphone(String phone);

    @Select("select * from user_table where id_card = #{idcard}")
    User Select_ByIdcard(String idcard);

    @Select("select * from user_table where account = #{account}")
    User Select_ByAccount( String account);

    @Insert("insert into user_table(phone,account,id_card,name) values(#{phone},#{account},#{id_card},#{name})")
    boolean Insert_User(User user);

    @Update("update user_table set name=#{name},phone=#{phone},id_card=#{id_card} where id=#{id}")
    boolean Update_User(User user);
    @Update("update user_table set phone=#{Phone} where account=#{Account}")
    void Update_Phone(@Param("Phone") String Phone, @Param("Account") String Account);
}
