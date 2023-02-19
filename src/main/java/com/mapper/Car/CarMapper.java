package com.mapper.Car;

import org.apache.ibatis.annotations.*;
import pojo.Car;

import java.util.ArrayList;


public interface CarMapper {
    @Select("select * from car_table")
    ArrayList<Car> Select_All();

    @Select("select * from car_table where license_plate = #{license_plate}")
    Car Select_ByLic(String license_plate);

    @Select("select * from car_table where location = #{loca}")
    ArrayList<Car> Select_ByLoca(int loca);

    @Select("select * from car_table where situation = #{situa}")
    ArrayList<Car> Select_Bysituation(@Param("situa")int situa);

    @Insert("insert into car_table(license_plate,rent,location,situation) values(#{lic},0,#{loca},1)")
    boolean Insert_Car(@Param("lic") String lic,@Param("loca") int loca);

    @Delete("delete from car_table where id=#{id}")
    boolean Delete_CarById(@Param("id") int id);

    @Update("update car_table set license_plate=#{license_plate},rent=#{isrent},location=#{location},situation=#{isnormal} where id=#{id}")
    boolean Update_Car_ById(Car car);
    @Update("update car_table set location=#{lcoation} where license_plate=#{lic}")
    boolean Update_Car_loca(@Param("lic") String lic,@Param("location") int loc);

    @Update("update car_table set situation=#{situa} where license_plate=#{lic}")
    boolean Update_Car_situa(@Param("situa")int situa,@Param("lic") String lic);

    @Update("update car_table set rent=1 where id=#{id}")
    void Update_Car_BeRent(@Param("id") int id);

    @Update("update car_table set rent=0 where license_plate=#{lic}")
    void Retu_Car(@Param("lic")String car_lic);
}
