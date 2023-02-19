package method.Car;

import com.mapper.Car.CarMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import pojo.Car;
import util.SqlSessionFactoryUtils;

import java.util.ArrayList;

/**
 * 操作car_table的方法封装
 */
public class Car_method {
    SqlSessionFactory factory= SqlSessionFactoryUtils.GetSqlSessionFactory();

    /**
     * 获取所有汽车信息
     * @return
     */
    public ArrayList<Car> GetAll_CarData(){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        ArrayList<Car> Cars = mapper.Select_All();
        sqlSession.close();
        return Cars;
    }

    /**
     * 据车牌号搜索指定汽车
     * @param lic   车牌号
     * @return  车牌号对应汽车
     */
    public Car select_ByLic(String lic){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        Car car=mapper.Select_ByLic(lic);
        sqlSession.close();
        return car;
    }

    /**
     * 获取指定站点里所有汽车集合
     * @param loca  站点
     * @return  该站点汽车集合
     */
    public ArrayList<Car> select_ByLoca(int loca){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        ArrayList<Car> cars=mapper.Select_ByLoca(loca);
        sqlSession.close();
        return cars;
    }

    /**
     * 获取（正常运行/损坏维修中）情况下的汽车集合
     * @param Situa 1-正常运行，0-损坏维修中
     * @return  指定情况下的汽车集合
     */
    public ArrayList<Car> select_BySitua(int Situa){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        ArrayList<Car> cars=mapper.Select_Bysituation(Situa);
        sqlSession.close();
        return cars;
    }

    /**
     * 新增汽车
     * @param car 新增汽车
     * @return
     */
    public boolean Insert_Car(@NotNull Car car){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        boolean r=mapper.Insert_Car(car.getLicense_plate(),car.getLocation());
        sqlSession.commit();
        sqlSession.close();
        return r;
    }

    /**
     * 删除指定car
     * @param id 删除car的id
     * @return
     */
    public boolean Delete_carById(int id){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        boolean r=mapper.Delete_CarById(id);
        sqlSession.commit();
        sqlSession.close();
        return r;
    }

    /**
     * 根据id更新汽车信息
     * @param car 更新数据
     * @return
     */
    public boolean Update_Car_ById(Car car){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        boolean o=mapper.Update_Car_ById(car);
        sqlSession.commit();
        sqlSession.close();
        return o;
    }
    /**
     *  更新car的站点
     * @param car   所需更新的汽车
     * @param loc   站点更新为
     * @return
     */
    public boolean Update_Car_Loca(@NotNull Car car, int loc){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        boolean o=mapper.Update_Car_loca(car.getLicense_plate(),loc);
        sqlSession.commit();
        sqlSession.close();
        return o;
    }



    /**
     * 更新car的情况
     * @param car   所需更新的汽车
     * @param situa 情况更新为
     * @return
     */
    public boolean Update_Car_Situa(@NotNull Car car, int situa){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        boolean o=mapper.Update_Car_situa(situa, car.getLicense_plate());
        sqlSession.commit();
        sqlSession.close();
        return o;
    }

    /**
     * 修改对应id汽车的租借情况
     * @param id
     */
    public void Car_BeRent(int id){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        mapper.Update_Car_BeRent(id);
        sqlSession.commit();
        sqlSession.close();
    }

    public void Retu_Car(String car_lic){
        SqlSession sqlSession=factory.openSession();
        CarMapper mapper=sqlSession.getMapper(CarMapper.class);

        mapper.Retu_Car(car_lic);
        sqlSession.commit();
        sqlSession.close();
    }


}
