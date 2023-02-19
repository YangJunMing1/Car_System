package GUI.Administrator;

import pojo.Record;
import util.Excel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 查看记录界面
 */
public class Record_interface extends JFrame {

    private ArrayList<Record> records;          //所有数据
    private ArrayList<Record> Current_records=new ArrayList<>();  //当前展示的数据
    private JComboBox<String> comboBox;   //搜索类型下拉框
    private JTextField search_text;      //搜索框
    private JTextField Min_Pay;         //搜索支付金额范围(最小)
    private JTextField Max_Pay;         //搜索支付金额范围(最大)
    private JTextField Car_lic1;        //车牌搜索框1
    private JTextField Car_lic2;        //车牌搜索框2
    /*时间搜索框*/
    private JTextField YY;
    private JTextField MM;
    private JTextField dd;
    private JTextField HH;
    private JTextField mm;
    private JTextField ss;

    private JButton search_button;        //搜索按钮

    //以下四者构成一个表单
    private String[] comboxTitle={"所有记录","用户账号", "身份证", "租借车_牌号", "租借日期","租借归还日期","订单完成","支付金额"};
    private String[] tableTitle={"用户账号", "身份证", "租借车_牌号", "租借日期","租借归还日期","订单完成","支付金额"};
    private DefaultTableModel dtm;
    private JTable table;

    private JPanel jPanel_N;
    private JScrollPane jPanel_Cen;
    private JPanel jPanel_S;

    private JTextField filed;   //文件名
    private JLabel Filed_xls;   //后缀名

    Record_interface(ArrayList<Record> R){
        records=R;
    }

    public void init(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("出租记录");
        this.setLayout(new BorderLayout());


        //北部组件
        set_North_Panel(0);

        //中间的表格
        search_All();

        //南部组件
        set_South_Panel();


        this.setVisible(true);
    }

    /**
     * 设置北部(上部)组件
     * @param a 4/5-日期框，7-金额范围框，其余 搜索框
     */
    private void set_North_Panel(int a){
        jPanel_N=new JPanel();
        comboBox=new JComboBox<>(comboxTitle);
        comboBox.setSelectedIndex(a);
        search_text=new JTextField(10);      //搜索框
        search_button=new JButton("搜索");        //搜索按钮
        jPanel_N.add(comboBox);
        if(a==3){
            Car_lic1=new JTextField(5);
            Car_lic2=new JTextField(7);
            JLabel Car_l=new JLabel("-");
            jPanel_N.add(Car_lic1);
            jPanel_N.add(Car_l);
            jPanel_N.add(Car_lic2);
        }
        else if(a==4||a==5){

            YY=new JTextField(5);
            MM=new JTextField(5);
            dd=new JTextField(5);
            HH=new JTextField(5);
            mm=new JTextField(5);
            ss=new JTextField(5);


            JLabel time1=new JLabel("-");
            JLabel time2=new JLabel("-");
            JLabel time3=new JLabel(" ");
            JLabel time4=new JLabel(":");
            JLabel time5=new JLabel(":");

            jPanel_N.add(YY);
            jPanel_N.add(time1);
            jPanel_N.add(MM);
            jPanel_N.add(time2);
            jPanel_N.add(dd);
            jPanel_N.add(time3);
            jPanel_N.add(HH);
            jPanel_N.add(time4);
            jPanel_N.add(mm);
            jPanel_N.add(time5);
            jPanel_N.add(ss);
            jPanel_N.add(search_button);
        }
        else if(a==7){
            Min_Pay=new JTextField(5);
            Max_Pay=new JTextField(5);
            jPanel_N.add(Min_Pay);
            jPanel_N.add(new JLabel("~"));
            jPanel_N.add(Max_Pay);
        }
        else if(a!=0){
            jPanel_N.add(search_text);
        }

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //根据选项不同，提供不同搜索框
                int a=comboBox.getSelectedIndex();
                if(a==0){
                    Frame_remove_Cen(jPanel_Cen);
                    search_All();
                }
                Frame_remove_N(jPanel_N);
                set_North_Panel(a);
            }
        });

        //搜索按钮监听器
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=comboBox.getSelectedIndex();
                if(a==0){       //查询所有
                    Frame_remove_Cen(jPanel_Cen);
                    search_All();
                }
                if(a==1){       //据用户账号查询
                    Frame_remove_Cen(jPanel_Cen);
                    search_Byusername(search_text.getText());
                }
                if(a==2){       //据身份证查询
                    String text=search_text.getText();
                    Frame_remove_Cen(jPanel_Cen);
                    search_Byidcard(text);
                }
                if(a==3){       //据车牌号查询
                    Frame_remove_Cen(jPanel_Cen);
                    search_ByCarlic(Car_lic1.getText(),Car_lic2.getText());
                }
                if(a==4){
                    Frame_remove_Cen(jPanel_Cen);
                    search_ByrentDate(YY.getText(),MM.getText(),dd.getText(),HH.getText(),mm.getText(),ss.getText());
                }
                if(a==5){
                    Frame_remove_Cen(jPanel_Cen);
                    search_ByretuDate(YY.getText(),MM.getText(),dd.getText(),HH.getText(),mm.getText(),ss.getText());
                }
                if(a==6){       //据订单完成情况查询
                    String text=search_text.getText();
                    Frame_remove_Cen(jPanel_Cen);
                    search_Finish(text);
                }
                if(a==7){       //据支付金额范围查询
                    Frame_remove_Cen(jPanel_Cen);
                    search_ByPay(Min_Pay.getText(),Max_Pay.getText());
                }

            }
        });

        if(a!=0)
            jPanel_N.add(search_button);
        this.add(jPanel_N,BorderLayout.NORTH);
        this.setVisible(true);
        this.repaint();
    }

    /**
     * 设置南部(下部)组件
     *
     */
    private void set_South_Panel(){
        //打印表单按钮添加
        jPanel_S=new JPanel();
        filed=new JTextField(7);   //文件名
        Filed_xls=new JLabel(".xsl");   //后缀名
        JButton button2=new JButton("打印表单");
        jPanel_S.add(filed);
        jPanel_S.add(Filed_xls);
        jPanel_S.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Print();
            }
        });
        this.add(jPanel_S,BorderLayout.SOUTH);
    }
    void search_All() {
        int record_num = records.size();
        Current_records.clear();
        String[][] tableDate = new String[record_num][7];
        for (int i = 0; i < record_num; i++) {
            tableDate[i][0] = records.get(i).getUser_name();
            tableDate[i][1] = records.get(i).getUser_idcard();
            tableDate[i][2] = records.get(i).getCar_lic();
            tableDate[i][3] = records.get(i).getrent_time();
            tableDate[i][4] = records.get(i).getRetu();
            if (records.get(i).isFinish()) {
                tableDate[i][5] = "是";
                tableDate[i][6] = String.valueOf(records.get(i).getPay());
            } else {
                tableDate[i][6] = "未完成订单";
                tableDate[i][5] = "否";
            }
            Current_records.add(records.get(i));
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    void search_Byusername(String username){
        int record_num = records.size();
        Current_records.clear();
        String right="^[0-9]*"+username+"[0-9]*$";

        String[][] tableDate = new String[record_num][7];
        ArrayList<Record> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(records.get(i).getUser_name().matches(right)){
                R.add(records.get(i));
                Current_records.add(records.get(i));
            }
        }
        record_num=R.size();
        for (int i = 0; i < record_num; i++) {
            tableDate[i][0] = R.get(i).getUser_name();
            tableDate[i][1] = records.get(i).getUser_idcard();

            tableDate[i][2] = R.get(i).getCar_lic();
            tableDate[i][3] = R.get(i).getrent_time();
            tableDate[i][4] = R.get(i).getRetu();
            if (R.get(i).isFinish()) {
                tableDate[i][5] = "是";
                tableDate[i][6] = String.valueOf(R.get(i).getPay());
            } else {
                tableDate[i][6] = "未完成订单";
                tableDate[i][5] = "否";
            }
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    void search_Byidcard(String id_card){
        int record_num = records.size();
        Current_records.clear();
        String right="^[0-9]*"+id_card+"[0-9]*$";

        String[][] tableDate = new String[record_num][7];
        ArrayList<Record> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(records.get(i).getUser_idcard().matches(right)){
                R.add(records.get(i));
                Current_records.add(records.get(i));
            }
        }
        record_num=R.size();
        for (int i = 0; i < record_num; i++) {
            tableDate[i][0] = R.get(i).getUser_name();
            tableDate[i][1] = records.get(i).getUser_idcard();

            tableDate[i][2] = R.get(i).getCar_lic();
            tableDate[i][3] = R.get(i).getrent_time();
            tableDate[i][4] = R.get(i).getRetu();
            if (R.get(i).isFinish()) {
                tableDate[i][5] = "是";
                tableDate[i][6] = String.valueOf(R.get(i).getPay());
            } else {
                tableDate[i][6] = "未完成订单";
                tableDate[i][5] = "否";
            }
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    void search_ByCarlic(String car_lic1,String car_lic2){
        int record_num = records.size();
        String right="^[\u4e00-\u9fa5]?"+car_lic1+"[A-Z]?"+"-"+"[0-9]*"+car_lic2+"[0-9]*[A-Z]?$";

        String[][] tableDate = new String[record_num][7];
        ArrayList<Record> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(records.get(i).getCar_lic().matches(right)){
                R.add(records.get(i));
                Current_records.add(records.get(i));
            }
        }
        record_num=R.size();
        for (int i = 0; i < record_num; i++) {
            tableDate[i][0] = R.get(i).getUser_name();
            tableDate[i][1] = records.get(i).getUser_idcard();

            tableDate[i][2] = R.get(i).getCar_lic();
            tableDate[i][3] = R.get(i).getrent_time();
            tableDate[i][4] = R.get(i).getRetu();
            if (R.get(i).isFinish()) {
                tableDate[i][5] = "是";
                tableDate[i][6] = String.valueOf(R.get(i).getPay());
            } else {
                tableDate[i][6] = "未完成订单";
                tableDate[i][5] = "否";
            }
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    void search_ByrentDate(String YY,String MM,String dd,String HH,String mm,String ss){
        int record_num = records.size();
        Current_records.clear();
        String right="^[0-9]*"+YY+"[0-9]*"+"-"
                +"[0-9]*"+MM+"[0-9]*"+"-"
                +"[0-9]*"+dd+"[0-9]*"+" "
                +"[0-9]*"+HH+"[0-9]*"+":"
                +"[0-9]*"+mm+"[0-9]*"+":"
                +"[0-9]*"+ss+"[0-9]*";

        String[][] tableDate = new String[record_num][7];
        ArrayList<Record> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(records.get(i).getrent_time().matches(right)){
                R.add(records.get(i));
                Current_records.add(records.get(i));
            }
        }
        record_num=R.size();
        for (int i = 0; i < record_num; i++) {
            tableDate[i][0] = R.get(i).getUser_name();
            tableDate[i][1] = records.get(i).getUser_idcard();

            tableDate[i][2] = R.get(i).getCar_lic();
            tableDate[i][3] = R.get(i).getrent_time();
            tableDate[i][4] = R.get(i).getRetu();
            if (R.get(i).isFinish()) {
                tableDate[i][5] = "是";
                tableDate[i][6] = String.valueOf(R.get(i).getPay());
            } else {
                tableDate[i][6] = "未完成订单";
                tableDate[i][5] = "否";
            }
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    void search_ByretuDate(String YY,String MM,String dd,String HH,String mm,String ss){
        int record_num = records.size();
        Current_records.clear();
        String right="^[0-9]*"+YY+"[0-9]*"+"-"
                +"[0-9]*"+MM+"[0-9]*"+"-"
                +"[0-9]*"+dd+"[0-9]*"+" "
                +"[0-9]*"+HH+"[0-9]*"+":"
                +"[0-9]*"+mm+"[0-9]*"+":"
                +"[0-9]*"+ss+"[0-9]*";

        String[][] tableDate = new String[record_num][7];
        ArrayList<Record> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(records.get(i).getRetu().matches(right)){
                R.add(records.get(i));
                Current_records.add(records.get(i));
            }
        }
        record_num=R.size();
        for (int i = 0; i < record_num; i++) {
            tableDate[i][0] = R.get(i).getUser_name();
            tableDate[i][1] = records.get(i).getUser_idcard();

            tableDate[i][2] = R.get(i).getCar_lic();
            tableDate[i][3] = R.get(i).getrent_time();
            tableDate[i][4] = R.get(i).getRetu();
            if (R.get(i).isFinish()) {
                tableDate[i][5] = "是";
                tableDate[i][6] = String.valueOf(R.get(i).getPay());
            } else {
                tableDate[i][6] = "未完成订单";
                tableDate[i][5] = "否";
            }
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }
    void search_Finish(String text){
        int record_num = records.size();
        Current_records.clear();
        String[][] tableDate = new String[record_num][7];
        for (int i = 0; i < record_num; i++) {
            if("是".equals(text)&&records.get(i).isFinish()){
                tableDate[i][0] = records.get(i).getUser_name();
                tableDate[i][1] = records.get(i).getUser_idcard();
                tableDate[i][2] = records.get(i).getCar_lic();
                tableDate[i][3] = records.get(i).getrent_time();
                tableDate[i][4] = records.get(i).getRetu();
                if (records.get(i).isFinish()) {
                    tableDate[i][5] = "是";
                    tableDate[i][6] = String.valueOf(records.get(i).getPay());
                } else {
                    tableDate[i][6] = "未完成订单";
                    tableDate[i][5] = "否";
                }
                Current_records.add(records.get(i));
            }
            else if("否".equals(text)&&!records.get(i).isFinish()){
                tableDate[i][0] = records.get(i).getUser_name();
                tableDate[i][1] = records.get(i).getUser_idcard();
                tableDate[i][2] = records.get(i).getCar_lic();
                tableDate[i][3] = records.get(i).getrent_time();
                tableDate[i][4] = records.get(i).getRetu();
                if (records.get(i).isFinish()) {
                    tableDate[i][5] = "是";
                    tableDate[i][6] = String.valueOf(records.get(i).getPay());
                } else {
                    tableDate[i][6] = "未完成订单";
                    tableDate[i][5] = "否";
                }
                Current_records.add(records.get(i));
            }

        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }
    void search_ByPay(String Min,String Max){
        int record_num = records.size();
        Current_records.clear();
        int min=Integer.parseInt(Min,10);
        int max=Integer.parseInt(Max,10);
        String[][] tableDate = new String[record_num][7];
        for (int i = 0; i < record_num; i++) {
            if(records.get(i).isFinish()&&records.get(i).getPay()<=max&&records.get(i).getPay()>=min){
                tableDate[i][0] = records.get(i).getUser_name();
                tableDate[i][1] = records.get(i).getUser_idcard();
                tableDate[i][2] = records.get(i).getCar_lic();
                tableDate[i][3] = records.get(i).getrent_time();
                tableDate[i][4] = records.get(i).getRetu();
                if (records.get(i).isFinish()) {
                    tableDate[i][5] = "是";
                    tableDate[i][6] = String.valueOf(records.get(i).getPay());
                } else {
                    tableDate[i][6] = "未完成订单";
                    tableDate[i][5] = "否";
                }
                Current_records.add(records.get(i));
            }
        }

        dtm = new DefaultTableModel(tableDate, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    /**
     * 打印当前表单
     */
    void Print(){
        JFileChooser fileChooser = new JFileChooser("D:\\");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            String filePath= fileChooser.getSelectedFile().getAbsolutePath();
            String File_name=filed.getText();
            if(File_name==null)
                File_name="Record";
            new Excel().Print_Record_Excel(Current_records,filePath,File_name);
        }
    }
    void Frame_remove_Cen(JScrollPane jsp){
        this.remove(jsp);
    }

    void Frame_remove_N(JPanel N){
        this.remove(N);
    }
}
