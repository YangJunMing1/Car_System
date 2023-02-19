package util.DataBase_control;

import java.sql.*;

/**
 * 含数据库与表单初始化的方法
 */
public class DataBase_Init {
    static final String JDBCDriver = "com.mysql.jdbc.Driver";
    static final String Url = "jdbc:mysql://localhost:3306/";
    static final String username = "root";
    static final String password = "77777777";
    static Connection conn = null;
    static Statement stmt = null;

    String Database="car_management_system";

    /**
     * 删除数据库
     */
    public void Drop_DataBase(){
        try {
            Class.forName(JDBCDriver);
            System.out.println("连接数据库");
            //连接数据库
            conn = DriverManager.getConnection(Url, username, password);
            //获取执行的SQL的对象
            stmt = conn.createStatement();
            String sql = "Drop DATABASE if  exists "+Database;
            stmt.executeUpdate(sql);
            System.out.println("数据库创建删除成功");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * 检测是否有数据库与表，无则创建
     */
    public void Create_DataBase() {
        try {
            Class.forName(JDBCDriver);
            System.out.println("连接数据库");
            //连接数据库
            conn = DriverManager.getConnection(Url, username, password);
            System.out.println("创建数据库");
            //获取执行的SQL的对象
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE if not exists "+Database;
            stmt.executeUpdate(sql);
            System.out.println("数据库创建成功");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public void Create_CarTable(){
        try {
            String Check_Table="show tables like 'car_table'";
            String Create_Table="create table "
                    +"car_table"
                    +"(id int primary key not null auto_increment,"
                    +"license_plate varchar(10) not null,"
                    +"rent int(1) not null,"
                    +"location int(1) not null,"
                    +"situation int(1) not null)";

            Class.forName(JDBCDriver);
            //连接数据库
            conn = DriverManager.getConnection(Url+Database ,username, password);
            //获取执行的SQL的对象
            stmt = conn.createStatement();

            ResultSet resultSet=stmt.executeQuery(Check_Table);
            if(resultSet.next()){
                System.out.println("Car表已存在");
            }
            else{
                if(stmt.executeUpdate(Create_Table)==0)
                    System.out.println("创建Car表成功");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void Create_UserTable(){
        try {
            String Check_Table="show tables like 'User_table'";
            String Create_Table="create table "
                    +"User_table"
                    +"(id int primary key not null auto_increment,"
                    +"name varchar(10) not null,"
                    +"phone varchar(11) not null,"
                    +"account varchar (12) not null,"
                    +"id_card varchar(21) not null)";

            Class.forName(JDBCDriver);
            //连接数据库
            conn = DriverManager.getConnection(Url+Database ,username, password);
            //获取执行的SQL的对象
            stmt = conn.createStatement();

            ResultSet resultSet=stmt.executeQuery(Check_Table);
            if(resultSet.next()){
                System.out.println("User表已存在");

            }
            else{
                if(stmt.executeUpdate(Create_Table)==0)
                    System.out.println("创建User表成功");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void Create_RecordTable(){
        try {
            String Check_Table="show tables like 'Record_table'";
            String Create_Table="create table "
                    +"Record_table"
                    +"(id int primary key not null auto_increment,"
                    +"user_name varchar(10) not null,"
                    +"user_idcard varchar(21) not null,"
                    +"car_lic varchar(10) not null,"
                    +"rent_time datetime,"
                    +"retu datetime,"
                    +"finish int(1),"
                    +"pay double)";


            Class.forName(JDBCDriver);
            //连接数据库
            conn = DriverManager.getConnection(Url+Database ,username, password);
            //获取执行的SQL的对象
            stmt = conn.createStatement();

            ResultSet resultSet=stmt.executeQuery(Check_Table);
            if(resultSet.next()){
                System.out.println("Record表已存在");
            }
            else{
                if(stmt.executeUpdate(Create_Table)==0)
                    System.out.println("创建Record表成功");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void Create_LoginTable(){
        try {
            String Check_Table="show tables like 'Login_table'";
            String Create_Table="create table "
                    +"Login_table"
                    +"(id int primary key not null auto_increment,"
                    +"login_user varchar(12) not null,"
                    +"login_pas varchar(10) not null,"
                    +"iden int (1) not null)";


            Class.forName(JDBCDriver);
            //连接数据库
            conn = DriverManager.getConnection(Url+Database ,username, password);
            //获取执行的SQL的对象
            stmt = conn.createStatement();

            ResultSet resultSet=stmt.executeQuery(Check_Table);
            if(resultSet.next()){
                System.out.println("login表已存在");

            }
            else {
                if(stmt.executeUpdate(Create_Table)==0)
                    System.out.println("创建login表成功");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
