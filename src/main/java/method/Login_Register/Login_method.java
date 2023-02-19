package method.Login_Register;

import com.mapper.Login.LoginMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.SqlSessionFactoryUtils;

public class Login_method {

    SqlSessionFactory factory= SqlSessionFactoryUtils.GetSqlSessionFactory();

    /**
     * 检测是否用户名重复
     */
    public boolean Check_reUser(String username){
        SqlSession sqlSession =factory.openSession();
        LoginMapper mapper=sqlSession.getMapper(LoginMapper.class);

       if(mapper.Get_IdByUsername(username)!=null)
           return true;
       else
           return false;

    }

    /**
     * 登录方法，需要对username与password先进行检测
     */
    public boolean Login(String username,String password){
        SqlSession sqlSession =factory.openSession();
        LoginMapper mapper=sqlSession.getMapper(LoginMapper.class);

        Integer id=mapper.Get_Id(username,password);
        if(id!=null)
            return true;
        else
            return false;
    }

    public  String Get_passwordByAcc(String account){
        SqlSession sqlSession =factory.openSession();
        LoginMapper mapper=sqlSession.getMapper(LoginMapper.class);

        String password =mapper.Get_Password_ByAccount(account);
        sqlSession.close();
        return password;
    }
    public String Get_iden(String username){
        SqlSession sqlSession =factory.openSession();
        LoginMapper mapper=sqlSession.getMapper(LoginMapper.class);

        int iden= mapper.Get_Iden(username);

        sqlSession.close();
        if(iden==1){
            return "管理员";
        }
        else{
            return "用户";
        }
    }

    /**
     * 用户密码表插入（注册时）
     */
    public void Insert(String account,String password){
        SqlSession sqlSession =factory.openSession();
        LoginMapper mapper=sqlSession.getMapper(LoginMapper.class);
        mapper.Insert(account,password);

        sqlSession.commit();
        sqlSession.close();
    }

    public void Update_pas_Byaccount(String password,String username){
        SqlSession sqlSession =factory.openSession();
        LoginMapper mapper=sqlSession.getMapper(LoginMapper.class);
        mapper.Update_Pasword(password,username);

        sqlSession.commit();
        sqlSession.close();
    }
}
