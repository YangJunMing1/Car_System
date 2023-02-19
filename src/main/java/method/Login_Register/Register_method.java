package method.Login_Register;

import method.User.User_method;
import pojo.User;

public class Register_method {


    /**
     * 注册功能的最终封装
     * @param name  姓名
     * @param password  密码
     * @param phone  手机号
     * @param id_card   身份证
     * @param username  账户
     */
    public void register(String name,String password,String phone,String id_card,String username){
        User user = new User(name, phone, id_card, username);

        //向数据库插入用户数据
        new User_method().Insert_user(user);
        //向数据库插入用户与密码
        new Login_method().Insert(username,password);

    }
}
