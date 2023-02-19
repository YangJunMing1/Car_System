package method.Excel;

import com.mapper.Excel.ExcelMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.SqlSessionFactoryUtils;

public class Excel_method {
    SqlSessionFactory factory= SqlSessionFactoryUtils.GetSqlSessionFactory();

    public void Output_to_carTable(String id,String license_plate,String rent,String location,String situation){
        SqlSession sqlSession=factory.openSession();
        ExcelMapper mapper=sqlSession.getMapper(ExcelMapper.class);

        mapper.Output_To_CarTable(id,license_plate,rent,location,situation);

        sqlSession.commit();
        sqlSession.close();
    }

    public void Output_to_LoginTable(String id,String login_user,String password,String iden){
        SqlSession sqlSession=factory.openSession();
        ExcelMapper mapper=sqlSession.getMapper(ExcelMapper.class);

        mapper.Output_To_LoginTable(Integer.valueOf(id),login_user,password,Integer.valueOf(iden));

        sqlSession.commit();
        sqlSession.close();
    }

    public void Output_to_recordTable(String id,String username,String useridcard,String license_plate,String rent,String retu,String finish,String pay){
        SqlSession sqlSession=factory.openSession();
        ExcelMapper mapper=sqlSession.getMapper(ExcelMapper.class);

        if(!"".equals(retu))
            mapper.Output_To_recordTable1(id,username,useridcard,license_plate,rent,retu,finish,pay);

        else
            mapper.Output_To_recordTable2(id,username,useridcard,license_plate,rent,finish,pay);

        sqlSession.commit();
        sqlSession.close();
    }

    public void Output_to_userTable(String id,String username,String phone,String account,String idcard){
        SqlSession sqlSession=factory.openSession();
        ExcelMapper mapper=sqlSession.getMapper(ExcelMapper.class);

        mapper.Output_To_userTable(id,username,phone,account,idcard);

        sqlSession.commit();
        sqlSession.close();
    }
}
