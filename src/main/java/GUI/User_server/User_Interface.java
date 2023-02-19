package GUI.User_server;

import method.Car.Car_method;
import method.Record.Record_method;
import pojo.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User_Interface extends JFrame{
    private String account;

    private JLabel Id;
    private JPanel myPanel;
    private Record record;
    private User_Interface UI;


    Font font=new Font("宋体",Font.BOLD,10);

    public User_Interface(String account){
        this.account=account;
        UI=this;
    }

    public void init(){

        myPanel=(JPanel) this.getContentPane();
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("共享汽车管理系统");
        this.setLayout(null);

        SetButton();

        this.setVisible(true);
    }

    private void SetButton(){
        Id=new JLabel(account+"用户，欢迎你");
        Id.setBounds(0,0,150,20);

        myPanel.add(Id);
        JButton Rent_Car=new JButton("我要租车");
        JButton Retu_Car=new JButton("我要还车");
        JButton Modify_Login=new JButton("修改个人信息");
        JLabel record_Text=new JLabel();
        record=Get_History_RentRecord();
        if(record==null){
            record_Text.setText("当前可以租车");
        }

        else{
            record_Text.setText("你有一笔 "+record.getrent_time()+" 时的订单未完成");
        }


        Rent_Car.setBounds(30,100,90,50);
        Retu_Car.setBounds(30,200,90,50);
        Modify_Login.setBounds(30,300,120,50);
        record_Text.setBounds(140,200,300,60);
        Rent_Car.setFont(font);
        Retu_Car.setFont(font);
        Modify_Login.setFont(font);

        myPanel.add(Rent_Car);
        myPanel.add(Retu_Car);
        myPanel.add(Modify_Login);
        myPanel.add(record_Text);

        Rent_Car.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(record!=null)
                    Tip("请先上次支付订单");
                else{
                    new RentCar_Interface(account,UI).Init();
                }

            }
        });
        Retu_Car.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(record!=null){
                    Date date=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String pay_time=sdf.format(date);
                    Timestamp d=Timestamp.valueOf(pay_time);
                    new Record_method().Pay(record,d);
                    new Car_method().Retu_Car(record.getCar_lic());
                    Tip("还车成功");
                    record=Get_History_RentRecord();
                    if(record==null)
                        record_Text.setText("当前可以租车");
                    else
                        record_Text.setText("你有一笔 "+record.getrent_time()+" 时的订单未完成");

                }
                Flush();
            }
        });

        Modify_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Update_UserDate(account).init_Upadte_interface();
            }
        });
    }

    /**
     * 获取历史未完成的订单
     */
    private Record Get_History_RentRecord(){
        return new Record_method().Get_NoPay_ByUsername(account);
    }

    /**
     * 刷新
     */
    public void Flush(){
        myPanel.removeAll();
        SetButton();
        myPanel.repaint();
    }

    public void Tip(String message){
        JOptionPane.showMessageDialog(this, message,
                "提示", 1);
    }
//
//    public static void main(String[] args) {
//        new User_Interface("2280078903").init();
//    }
}
