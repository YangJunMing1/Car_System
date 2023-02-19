package GUI.Administrator;

import method.Car.Car_method;
import method.Record.Record_method;
import pojo.Car;
import pojo.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Administrator extends JFrame {

    private String account; //管理员账户
    private JPanel jPanel;
    private JButton control_car;    //管理车辆选项
    private JButton control_record;    //管理记录选项
    private JLabel Id;
    Font font=new Font("宋体",Font.BOLD,10);

    public Administrator(String account){
        this.account=account;
    }

    //页面初始化
    public void init(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("共享汽车管理系统");

        Init_interface();
        this.setVisible(true);
    }

    /**
     * 初始界面布局
     */
    public void Init_interface(){
        this.setLayout(null);


        jPanel=(JPanel) this.getContentPane();
        jPanel.setLayout(null);


        Id=new JLabel(account+"管理员，你好");
        control_car=new JButton("车辆管理");
        control_record=new JButton("查看业绩");

        control_car.setFont(font);
        control_record.setFont(font);

        Id.setBounds(0,0,150,20);
        control_car.setBounds(50,100,85,50);
        control_record.setBounds(50,175,85,50);

        jPanel.add(Id);
        jPanel.add(control_car);
        jPanel.add(control_record);

        control_car.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Car> records=new Car_method().GetAll_CarData();
                new Car_control_interface(records).init();
            }
        });
        control_record.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Record> records=new Record_method().Get_AllRecord();
                //打开记录界面
                new Record_interface(records).init();
            }
        });

    }


    public static void main(String[] args) {
        new Administrator("2280078903").init();
    }
}
