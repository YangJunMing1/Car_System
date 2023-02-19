package GUI.User_server;

import method.Login_Register.Login_method;
import method.User.User_method;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update_UserDate extends JFrame {
    JPanel myPanel;
    String account;
    User user;
    JLabel phone_warn;
    JLabel pas_warn;
    boolean phone_T=true;
    boolean pas_T=true;
    boolean success=false;


    Update_UserDate(String account){
        this.account=account;
    }

    /**
     * 修改用户信息界面
     */
    public void init_Upadte_interface(){
        user = new User_method().Select_ByAccount(account);
        String Password=new Login_method().Get_passwordByAcc(account);
        myPanel=(JPanel) this.getContentPane();
        myPanel.setLayout(null);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("个人信息修改");
        this.setLayout(null);
        JLabel account_Text=new JLabel("账号："+account);
        JLabel password_Text=new JLabel("密码：");
        JTextField password=new JTextField();
        JLabel phone_Text=new JLabel("手机");
        JTextField phone=new JTextField();

        pas_warn=new JLabel();
        phone_warn=new JLabel();

        account_Text.setBounds(100,100,200,70);
        password_Text.setBounds(100,150,100,70);
        password.setBounds(140,175,100,20);
        password.setText(Password);
        phone_Text.setBounds(100,190,100,70);
        phone.setBounds(140,215,100,20);
        phone.setText(user.getPhone());

        pas_warn.setBounds(250,175,400,20);
        pas_warn.setForeground(Color.red);
        phone_warn.setBounds(250,215,150,20);
        phone_warn.setForeground(Color.red);

        JButton update=new JButton("修改");
        update.setBounds(180,350,75,50);
        myPanel.add(account_Text);
        myPanel.add(password_Text);
        myPanel.add(password);
        myPanel.add(phone_Text);
        myPanel.add(phone);
        myPanel.add(update);

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!success){
                    Judge_Phone_warn(phone.getText());
                    Judge_Pas_warn(password.getText());
                }
                else success=false;

            }
        });


        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Judge_Phone_warn(phone.getText());
                Judge_Pas_warn(password.getText());
                if(!pas_T||!phone_T)
                    Tip("请按要求修改");
                else{
                    new Login_method().Update_pas_Byaccount(password.getText(),account);
                    new User_method().Update_phone(phone.getText(),account);
                    Tip("修改成功");
                    success=true;
                    myPanel.remove(phone_warn);
                    myPanel.remove(pas_warn);
                    myPanel.repaint();
                }
            }
        });
        this.setVisible(true);
    }

    public boolean check_phone(String phone){
        if(phone.isEmpty())
            return false;
        String right="^[1]{1}[0-9]{10}$";
        return phone.matches(right);
    }
    public boolean check_password(String pas){
        if(pas.isEmpty())
            return false;
        String right="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,9}$";
        return pas.matches(right);
    }
    public void Judge_Phone_warn(String R){
        if(!check_phone(R)){
            phone_warn.setText("请输入11位正确手机号");
            myPanel.add(phone_warn);
            myPanel.repaint();
            phone_T=false;
        } else if (R.equals(user.getPhone())) {
            myPanel.remove(phone_warn);
            myPanel.repaint();
        } else if (new User_method().Select_ByPhone(R)!=null) {
            phone_warn.setText("该手机已被注册");
            myPanel.add(phone_warn);
            myPanel.repaint();
            phone_T=false;
        } else{
            myPanel.remove(phone_warn);
            myPanel.repaint();
            phone_T=true;
        }
    }
    public void Judge_Pas_warn(String P){
        if(!check_password(P)){
            pas_warn.setText("6~9位并包含以下类型(a-z,A-Z,0-9)");
            myPanel.add(pas_warn);
            myPanel.repaint();
            pas_T=false;
        }
        else{
            myPanel.remove(pas_warn);
            myPanel.repaint();
            pas_T=true;
        }
    }

    public void Tip(String message){
        JOptionPane.showMessageDialog(this, message,
                "提示", 1);
    }

    public static void main(String[] args) {
        new Update_UserDate("2280078903").init_Upadte_interface();
    }
}
