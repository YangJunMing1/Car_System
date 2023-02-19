package method.User;

import com.mapper.User.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.User;
import util.SqlSessionFactoryUtils;

import java.util.ArrayList;

/**
 * 操作user_table的方法封装
 */
public class User_method {
    SqlSessionFactory factory= SqlSessionFactoryUtils.GetSqlSessionFactory();

    /**
     * 查询所有用户
     * @return 所查询到的用户信息集合
     */
    public ArrayList<User> SelectAll(){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        ArrayList<User> arrayList=mapper.Select_All();
        sqlSession.close();
        return arrayList;
    }

    /**
     * 查询指定id的用户信息
     * @param id id
     * @return 所查询到的用户信息
     */
    public User Select_ById(int id){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        User user=mapper.Select_ById(id);
        sqlSession.close();
        return user;
    }

    /**
     * 据手机号查询指定用户
     * @param phone 手机号
     * @return 所查询到的用户信息
     */
    public User Select_ByPhone(String phone){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        User user=mapper.Select_Byphone(phone);
        sqlSession.close();
        return user;
    }

    /**
     * 据身份证查询指定用户
     * @param id_card 身份证
     * @return 所查询到的用户信息
     */
    public User Select_ByIdcard(String id_card){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        User user=mapper.Select_ByIdcard(id_card);
        sqlSession.close();
        return user;
    }

    /**
     * 据账号查询指定用户
     * @param account 账户
     * @return 所查询到的用户信息
     */
    public User Select_ByAccount(String account){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        User user=mapper.Select_ByAccount(account);
        sqlSession.close();
        return user;
    }


    /**
     * 新增用户（用于注册）
     * @param user 注册的用户信息
     * @return
     */
    public boolean Insert_user(User user){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        boolean b=mapper.Insert_User(user);
        sqlSession.commit();
        sqlSession.close();
        return b;
    }

    /**
     * 用户修改信息
     * @param user 修改的用户信息
     * @return
     */
    public boolean Update_user(User user){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        boolean b=mapper.Update_User(user);
        sqlSession.commit();
        sqlSession.close();
        return b;
    }

    public void Update_phone(String phone,String account){
        SqlSession sqlSession =factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        mapper.Update_Phone(phone,account);
        sqlSession.commit();
        sqlSession.close();
    }


}
