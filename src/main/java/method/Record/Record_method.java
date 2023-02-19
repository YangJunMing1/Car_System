package method.Record;

import com.mapper.Record.RecordMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Record;
import util.SqlSessionFactoryUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Record_method {
    SqlSessionFactory factory= SqlSessionFactoryUtils.GetSqlSessionFactory();

    /**
     * 获取所有订单记录
     * @return
     */
    public ArrayList<Record> Get_AllRecord(){
        SqlSession sqlSession=factory.openSession();
        RecordMapper mapper=sqlSession.getMapper(RecordMapper.class);

        ArrayList<Record> records=mapper.SelectAll();;
        sqlSession.close();
        return records;
    }

    /**
     * 据用户名搜索该用户未支付的订单
     * @param username  用户名
     * @return  未支付的订单记录
     */
    public Record Get_NoPay_ByUsername(String username){
        SqlSession sqlSession=factory.openSession();
        RecordMapper mapper=sqlSession.getMapper(RecordMapper.class);

        Record record=mapper.Select_ByUsername(username,"0");
        sqlSession.close();
        return record;
    }

    /**
     * 支付record订单
     * @param record  未支付的订单
     */
    public void Pay(Record record, Timestamp pay_time){
        SqlSession sqlSession=factory.openSession();
        RecordMapper mapper=sqlSession.getMapper(RecordMapper.class);

        mapper.Pay(pay_time,record.getUser_name(),record.getCar_lic());
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 插入新订单
     * @param user_name
     * @param idcard
     * @param Car_lic
     * @param rent_Time
     */
    public void Insert_Record(String user_name,String idcard,String Car_lic,Timestamp rent_Time){
        SqlSession sqlSession=factory.openSession();
        RecordMapper mapper=sqlSession.getMapper(RecordMapper.class);
        mapper.Insert_Record(user_name,idcard,Car_lic,rent_Time);
        sqlSession.commit();
        sqlSession.close();
    }
}
