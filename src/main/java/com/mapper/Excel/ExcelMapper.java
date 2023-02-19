package com.mapper.Excel;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ExcelMapper {
    @Insert("insert into car_table values(#{id},#{license_plate},#{rent},#{location},#{situation})")
    public void Output_To_CarTable(@Param("id") String id,@Param("license_plate") String licenseplate,@Param("rent") String rent,@Param("location") String location,@Param("situation") String situation);

    @Insert("insert into login_table values(#{id},#{login_user},#{login_pas},#{iden})")
    public void Output_To_LoginTable(@Param("id") Integer id,@Param("login_user") String login_user,@Param("login_pas") String password,@Param("iden") Integer identity);

    @Insert("insert into record_table  values (#{id},#{user_name},#{user_idcard},#{car_lic},#{rent_time},#{retu},#{finish},#{pay})")
    public void Output_To_recordTable1(@Param("id") String id, @Param("user_name")String username, @Param("user_idcard") String idcard, @Param("car_lic") String lic, @Param("rent_time") String time, @Param("retu") String retuTime, @Param("finish")String finish, @Param("pay") String pay);

    @Insert("insert into record_table (id,user_name,user_idcard,car_lic,rent_time,finish,pay) values (#{id},#{user_name},#{user_idcard},#{car_lic},#{rent_time},#{finish},#{pay})")
    public void Output_To_recordTable2(@Param("id") String id, @Param("user_name")String username, @Param("user_idcard") String idcard, @Param("car_lic") String lic, @Param("rent_time") String time,  @Param("finish")String finish, @Param("pay") String pay);
    @Insert("insert into user_table  values (#{id},#{name},#{phone},#{account},#{id_card})")
    public void Output_To_userTable(@Param("id") String id,@Param("name")String username,@Param("phone") String phone,@Param("account")String account,@Param("id_card")String id_card);

}

