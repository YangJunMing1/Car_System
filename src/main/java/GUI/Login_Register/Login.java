package GUI.Login_Register;

import GUI.Administrator.Administrator;
import GUI.User_server.User_Interface;
import method.Login_Register.Login_method;
import method.Login_Register.Register_method;
import method.User.User_method;
import util.DataBase_control.Init_Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Login extends JFrame {

    JPanel jPanel;
    ImageIcon background;
    JLabel label_back; //放置背景图片的标签
    JLabel root_text;   //账户文本标签
    JLabel pas_text;    //密码文本标签
    JLabel phone_text;       //手机文本标签
    JLabel id_card_text;     //身份证文本标签
    JLabel name_text;      //姓名文本标签
    JLabel root_warn;
    JLabel pas_warn;
    JLabel phone_warn;
    JLabel id_warn;
    JLabel name_warn;
    /*以下4个布尔值为true才释放注册按钮*/
    boolean root_T=false;
    boolean pas_T=false;
    boolean phone_T=false;
    boolean id_T=false;
    boolean name_T=false;
    JTextField root_l;     //账户文本框（登录）
    JTextField password_l; //密码文本框（登录）
    JTextField root;     //账户文本框(注册)
    JTextField password; //密码文本框（注册）
    JTextField phone;    //手机文本框
    JTextField id_card;  //身份证文本框
    JTextField name;     //姓名文本框
    JButton login;      //登录按钮
    JButton register;    //注册按钮
    JButton back;   //返回按钮



    Font font=new Font("宋体",Font.BOLD,9);

    public Login(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);

    }

    public void init(){
        jPanel=(JPanel) this.getContentPane();
        jPanel.setLayout(null);
//        background=new ImageIcon("src/png/1.png");
//        SetBackground();
//        this.getLayeredPane().add(label_back,Integer.MIN_VALUE);
        this.setTitle("共享汽车管理系统");



        paint_login();

    }



    /**
     * 绘制登录界面
     */
    public void paint_login(){
        root_text=new JLabel("账号：");
        pas_text=new JLabel("密码：");
        this.add(root_text);
        this.add(pas_text);
        root_text.setBounds(getWidth()/2-150,getHeight()/2-120,50,50);
        pas_text.setBounds(getWidth()/2-150,getHeight()/2-70,50,50);

        root_l=new JTextField(15);
        password_l=new JTextField(15);
        this.add(root_l);
        this.add(password_l);
        root_l.setBounds(getWidth()/2-100,getHeight()/2-100,200,20);
        password_l.setBounds(getWidth()/2-100,getHeight()/2-50,200,20);

        login=new JButton("登录");
        register=new JButton("注册");
        JButton Init_DataBase_Sure=new JButton("初始化数据库");
        this.add(login);
        this.add(register);
        this.add(Init_DataBase_Sure);
        login.setBounds(getWidth()/2-100,getHeight()/2-20,75,40);
        register.setBounds(getWidth()/2,getHeight()/2-20,75,40);
        Init_DataBase_Sure.setBounds(330,30,150,50);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //调用登录封装接口
                String username=root_l.getText();
                String password=password_l.getText();
                if(check_root(username)&&check_password(password)){
                    Login_method login_method = new Login_method();
                    if(login_method.Login(username,password)){
                        Tip("登录成功");
                        Jump(login_method.Get_iden(username),username);
                    }
                    else{
                        Error("用户或密码错误");
                    }
                }
                else{
                    Error("用户或密码错误");
                }


            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //创建注册窗口
                jPanel.removeAll();
                paint_regist();
                jPanel.repaint();
            }
        });
        Init_DataBase_Sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null, "你确定要初始化(重置)数据库吗", null,JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION)){
                    new Init_Data().Init();
                    Tip("初始化完成");
                }

            }
        });
    }

    /**
     * 绘制注册界面
     */
    public void paint_regist(){
        root_text=new JLabel(" 账号：");
        pas_text=new JLabel(" 密码：");
        phone_text=new JLabel("手机号：");
        id_card_text=new JLabel("身份证：");
        name_text=new JLabel("姓名");
        this.add(root_text);
        this.add(pas_text);
        this.add(phone_text);
        this.add(id_card_text);
        this.add(name_text);
        root_text.setBounds(getWidth()/2-150,getHeight()/2-100,50,50);
        pas_text.setBounds(getWidth()/2-150,getHeight()/2-70,50,50);
        phone_text.setBounds(getWidth()/2-150,getHeight()/2-40,50,50);
        id_card_text.setBounds(getWidth()/2-150,getHeight()/2-10,50,50);
        name_text.setBounds(getWidth()/2-150,getHeight()/2+20,50,50);

        root_warn=new JLabel("请输入9-12位数字");
        pas_warn=new JLabel("要求6~9位并包含字母和数字");
        phone_warn=new JLabel("请输入11位正确手机号");
        id_warn=new JLabel("请输入正确的身份证");
        name_warn=new JLabel("请输入2~10位中文");
        root_warn.setForeground(Color.red);
        pas_warn.setForeground(Color.red);
        phone_warn.setForeground(Color.red);
        id_warn.setForeground(Color.red);
        name_warn.setForeground(Color.red);
        root_warn.setBounds(getWidth()/2+100,getHeight()/2-100,100,50);
        pas_warn.setBounds(getWidth()/2+100,getHeight()/2-70,400,50);
        phone_warn.setBounds(getWidth()/2+100,getHeight()/2-40,200,50);
        id_warn.setBounds(getWidth()/2+100,getHeight()/2-10,200,50);
        name_warn.setBounds(getWidth()/2+100,getHeight()/2+20,200,50);

        root=new JTextField(15);
        password=new JTextField(15);
        id_card=new JTextField(15);
        phone=new JTextField(15);
        name=new JTextField(15);
        this.add(root);
        this.add(password);
        this.add(phone);
        this.add(id_card);
        this.add(name);
        root.setBounds(getWidth()/2-100,getHeight()/2-85,200,20);
        password.setBounds(getWidth()/2-100,getHeight()/2-55,200,20);
        phone.setBounds(getWidth()/2-100,getHeight()/2-25,200,20);
        id_card.setBounds(getWidth()/2-100,getHeight()/2+5,200,20);
        name.setBounds(getWidth()/2-100,getHeight()/2+35,200,20);
        final int[] password_click= { 0 };
        final int[] root_click = { 0 };
        final int[] phone_click={ 0 };
        final int[] id_click={ 0 };
        final int[] name_click={0};
        root.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                root_click[0]++;
                if(phone_click[0] >=1){
                    Judge_Phone_warn();
                }
                if(password_click[0]>=1){
                    Judge_Pas_warn();
                }
                if(id_click[0]>=1){
                    Judge_id_warn();
                }
                if(name_click[0]>=1){
                    Judge_name_warn();
                }
            }
        });
        password.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                password_click[0]++;
                if(phone_click[0] >=1){
                    Judge_Phone_warn();
                }
                if(root_click[0]>=1){
                    Judge_Root_warn();
                }
                if(id_click[0]>=1){
                    Judge_id_warn();
                }
                if(name_click[0]>=1){
                    Judge_name_warn();
                }
            }
        });
        id_card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id_click[0]++;
                if(phone_click[0] >=1){
                    Judge_Phone_warn();
                }
                if(password_click[0]>=1){
                    Judge_Pas_warn();
                }
                if(root_click[0]>=1){
                    Judge_Root_warn();
                }
                if(name_click[0]>=1){
                    Judge_name_warn();
                }
            }
        });
        phone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                phone_click[0]++;
                if(id_click[0] >=1){
                    Judge_id_warn();
                }
                if(password_click[0]>=1){
                    Judge_Pas_warn();
                }
                if(root_click[0]>=1){
                    Judge_Root_warn();
                }
                if(name_click[0]>=1){
                    Judge_name_warn();
                }

            }
        });
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name_click[0]++;
                if(id_click[0] >=1){
                    Judge_id_warn();
                }
                if(password_click[0]>=1){
                    Judge_Pas_warn();
                }
                if(root_click[0]>=1){
                    Judge_Root_warn();
                }
                if(phone_click[0] >=1){
                    Judge_Phone_warn();
                }
            }
        });
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(root_click[0]>=1){
                    Judge_Root_warn();
                }
                if(password_click[0]>=1){
                    Judge_Pas_warn();
                }
                if(id_click[0] >=1){
                    Judge_id_warn();
                }
                if(phone_click[0] >=1){
                    Judge_Phone_warn();
                }
                if(name_click[0]>=1){
                    Judge_name_warn();
                }
            }
        });

        back=new JButton("返回登录");
        register=new JButton("注册");
        back.setFont(font);
        register.setFont(font);
        this.add(back);
        this.add(register);
        back.setBounds(getWidth()/2-100,getHeight()/2+65,75,40);
        register.setBounds(getWidth()/2,getHeight()/2+65,75,40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name_click[0]=phone_click[0]=id_click[0]=root_click[0]=password_click[0]=0;

                jPanel.removeAll();
                paint_login();
                jPanel.repaint();
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //注册
                if(root_T&&pas_T&&id_T&&phone_T){
                    root_T=pas_T=id_T=phone_T=false;

                    new Register_method().register(name.getText(),password.getText(),phone.getText(),id_card.getText(),root.getText());
                    Tip("注册成功");
                    name_click[0]=phone_click[0]=id_click[0]=root_click[0]=password_click[0]=0;
                    root_l.setText("");
                    password_l.setText("");
                    root.setText("");
                    password.setText("");
                    phone.setText("");
                    id_card.setText("");
                    name.setText("");

                }
                else
                    Error("请检查已是否按提示注册");
            }
        });
    }

    public boolean check_root(String root){
        if(root.isEmpty())
            return false;
        String right="^[0-9]{9,12}$";
        return root.matches(right);
    }
    public boolean check_phone(String phone){
        if(phone.isEmpty())
            return false;
        String right="^[1]{1}[0-9]{10}$";
        return phone.matches(right);
    }
    public boolean check_id(String id){
        if(id.isEmpty())
            return false;
        String right="^[0-9]{17}[0-9X]{1}$";
        return id.matches(right);
    }
    public boolean check_password(String pas){
        if(pas.isEmpty())
            return false;
        String right="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,9}$";
        return pas.matches(right);
    }
    public boolean check_name(String name){
        if(name.isEmpty())
            return false;
        String right="^[\u4e00-\u9fa5]{2,10}$";
        return name.matches(right);
    }
    /**
     * 判断是否发出用户名不正确警告
     */
    public void Judge_Root_warn(){
        String R=root.getText();
        if(!check_root(R)){
            root_warn.setText("请输入9-12位数字");
            jPanel.add(root_warn);
            jPanel.repaint();
            root_T=false;
        } else if (new Login_method().Check_reUser(R)) {
            root_warn.setText("用户名已被注册");
            jPanel.add(root_warn);
            jPanel.repaint();
            root_T=false;
        } else{
            jPanel.remove(root_warn);
            jPanel.repaint();
            root_T=true;
        }
    }
    /**
     * 判断是否发出密码不正确警告
     */
    public void Judge_Pas_warn(){
        String P=password.getText();
        if(!check_password(P)){
            jPanel.add(pas_warn);
            jPanel.repaint();
            pas_T=false;
        }
        else{
            jPanel.remove(pas_warn);
            jPanel.repaint();
            pas_T=true;
        }
    }
    /**
     * 判断是否发出手机号不正确警告
     */
    public void Judge_Phone_warn(){
        String R=phone.getText();
        if(!check_phone(R)){
            phone_warn.setText("请输入11位正确手机号");
            jPanel.add(phone_warn);
            jPanel.repaint();
            phone_T=false;
        } else if (new User_method().Select_ByPhone(R)!=null) {
            phone_warn.setText("该手机已被注册");
            jPanel.add(phone_warn);
            jPanel.repaint();
            phone_T=false;
        } else{
            jPanel.remove(phone_warn);
            jPanel.repaint();
            phone_T=true;
        }
    }
    /**
     * 判断是否发出身份证不正确警告
     */
    public void Judge_id_warn(){
        String R=id_card.getText();
        if(!check_id(R)){
            id_warn.setText("请输入正确的身份证");
            jPanel.add(id_warn);
            jPanel.repaint();
            id_T=false;
        } else if (new User_method().Select_ByIdcard(R)!=null) {
            id_warn.setText("该身份证已被注册");
            jPanel.add(id_warn);
            jPanel.repaint();
            id_T=false;
        } else{
            jPanel.remove(id_warn);
            jPanel.repaint();
            id_T=true;
        }
    }
    public void Judge_name_warn(){
        String R=name.getText();
        if(!check_name(R)){
            jPanel.add(name_warn);
            jPanel.repaint();
            id_T=false;
        }
        else{
            jPanel.remove(name_warn);
            jPanel.repaint();
            id_T=true;
        }
    }


    /**
     * 跳转
     * @param identity 用户身份（用户/管理员）
     */
    public void Jump(String identity,String username){
        this.dispose();
        if("管理员".equals(identity)){
            this.dispose();
            new Administrator(username).init();
        }
        else{
            this.dispose();
            new User_Interface(username).init();
        }
    }

    /**
     * 弹出警告
     * @param message 警告文本
     */
    public void Error(String message){
        JOptionPane.showMessageDialog(this, message,
                "错误", JOptionPane.WARNING_MESSAGE);
    }

    public void Tip(String message){
        JOptionPane.showMessageDialog(this, message,
                "提示", 1);
    }

    public static void main(String[] args) {
        new Login();
    }

}
