package com.mapper.Record;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pojo.Record;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface RecordMapper {

    @Select("select * from record_table")
    public ArrayList<Record> SelectAll();

    @Select("select * from record_table where user_name=#{username} and finish=#{nofinish}")
    public Record Select_ByUsername(@Param("username") String username,@Param("nofinish") String finish);

    @Update("update record_table set retu=#{pay_time},finish=1,pay=5 where user_name= #{username} and car_lic = #{carlic} ")
    public void Pay(@Param("pay_time")Timestamp pay_time, @Param("username") String username , @Param("carlic") String carlic);

    @Insert("insert into record_table (user_name,user_idcard,car_lic,rent_time,finish) values(#{user_name},#{idCard},#{car_lic},#{rent_time},0)")
    public void Insert_Record(@Param("user_name") String user_name,@Param("idCard")String idcard,@Param("car_lic") String Car_lic,@Param("rent_time") Timestamp rent_Time);
}

//,