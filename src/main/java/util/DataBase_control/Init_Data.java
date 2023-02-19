package util.DataBase_control;

import util.Excel;

public class Init_Data {
    /**
     * 初始化数据库
     */
    public void Init(){
        DataBase_Init dataBase_init = new DataBase_Init();
        dataBase_init.Drop_DataBase();
        dataBase_init.Create_DataBase();

        dataBase_init.Create_CarTable();
        dataBase_init.Create_LoginTable();
        dataBase_init.Create_RecordTable();
        dataBase_init.Create_UserTable();

        new Excel().readExcel("src/testfile/TestFile.xls");
    }

    public static void main(String[] args) {
        new Init_Data().Init();
        new Excel().readExcel(null);
    }
}
