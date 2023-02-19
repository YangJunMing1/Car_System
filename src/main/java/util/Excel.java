package util;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import method.Excel.Excel_method;
import pojo.Record;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 包含写入和读取Excel文件的方法
 */
public class Excel {

    /**
     * 打印输出租借记录数据（管理员专属）
     * @param Data 数据
     */
    public boolean Print_Record_Excel(ArrayList<Record> Data,String filePath,String filename){
        String[] titleA = {"id","用户账号","用户身份证","车牌号","租用时间","归还时间","归还情况","支付金额"};
        //创建Excel文件，B库CD表文件
        File fileA = new File(filePath+filename+".xls");
        if(fileA.exists()){
            //如果文件存在就删除
            fileA.delete();
        }
        try {
            fileA.createNewFile();
            //创建工作簿
            WritableWorkbook workbookA = Workbook.createWorkbook(fileA);
            //创建sheet
            WritableSheet sheetA = workbookA.createSheet("sheet1", 0);
            Label labelA = null;
            //设置列名
            for (int i = 0; i < titleA.length; i++) {
                labelA = new Label(i,0,titleA[i]);
                sheetA.addCell(labelA);
            }
            //获取数据源
            for (int i = 0; i < Data.size(); i++) {
                labelA = new Label(0,i+1,String.valueOf(i)+1);
                sheetA.addCell(labelA);
                labelA = new Label(1,i+1,Data.get(i).getUser_name());
                sheetA.addCell(labelA);
                labelA = new Label(2,i+1,Data.get(i).getUser_idcard());
                sheetA.addCell(labelA);
                labelA = new Label(3,i+1,Data.get(i).getCar_lic());
                sheetA.addCell(labelA);
                labelA = new Label(4,i+1,Data.get(i).getrent_time());
                sheetA.addCell(labelA);
                labelA = new Label(5,i+1,Data.get(i).getRetu());
                sheetA.addCell(labelA);
                labelA = new Label(6,i+1,Data.get(i).isFinish()?"已归还":"未完成订单");
                sheetA.addCell(labelA);
                labelA = new Label(7,i+1,String.valueOf(Data.get(i).getPay()));
                sheetA.addCell(labelA);
            }
            workbookA.write();    //写入数据
            workbookA.close();  //关闭连接
            System.out.println("成功写入文件，请前往查看文件！");
            return true;
        } catch (Exception e) {
            System.out.println("文件写入失败，报异常...");
            return false;
        }

    }

    public void readExcel(String file_path) {
        try {
            // 创建输入流，读取Excel
//            InputStream is = new FileInputStream(file.getAbsolutePath());
            InputStream is = new FileInputStream(file_path);
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 1; i < sheet.getRows(); i++) {
                    if("car_table".equals(sheet.getName())){
                        String id=sheet.getCell(0, i).getContents();
                        String license_plate=sheet.getCell(1, i).getContents();
                        String rent=sheet.getCell(2, i).getContents();
                        String location=sheet.getCell(3, i).getContents();
                        String situation=sheet.getCell(4, i).getContents();
                        new Excel_method().Output_to_carTable(id,license_plate,rent,location,situation);
                    }
                    if("login_table".equals(sheet.getName())){
                        String id=sheet.getCell(0, i).getContents();
                        String user=sheet.getCell(1, i).getContents();
                        String pas=sheet.getCell(2, i).getContents();
                        String iden=sheet.getCell(3, i).getContents();
                       new Excel_method().Output_to_LoginTable(id,user,pas,iden);
                    }
                    if("record_table".equals(sheet.getName())){
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String id=sheet.getCell(0, i).getContents();
                        String user_name=sheet.getCell(1, i).getContents();
                        String uesr_idcard=sheet.getCell(2, i).getContents();
                        String car_lic=sheet.getCell(3, i).getContents();

                        DateCell c=(DateCell) sheet.getCell(4, i);
                        Date d=c.getDate();
                        String rent_time=sdf.format(d);

                        String retu="";
                        if(!sheet.getCell(5, i).getContents().equals("")){
                            c=(DateCell) sheet.getCell(5, i);
                            d =c.getDate();
                            retu=sdf.format(d);
                        }
                        String finish=sheet.getCell(6, i).getContents();
                        String pay=sheet.getCell(7, i).getContents();
                        new Excel_method().Output_to_recordTable(id,user_name,uesr_idcard,car_lic,rent_time,retu,finish,pay);
                    }
                    if("user_table".equals(sheet.getName())){
                        String id=sheet.getCell(0, i).getContents();
                        String name=sheet.getCell(1, i).getContents();
                        String phone=sheet.getCell(2, i).getContents();
                        String account=sheet.getCell(3, i).getContents();
                        String id_card=sheet.getCell(4, i).getContents();
                        new Excel_method().Output_to_userTable(id,name,phone,account,id_card);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Excel().readExcel(null);
    }
}
