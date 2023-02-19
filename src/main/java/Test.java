import com.mapper.Car.CarMapper;
import config.springConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.Car;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(springConfig.class);

        CarMapper bean = ctx.getBean(CarMapper.class);

        ArrayList<Car> cars = bean.Select_All();

        for(Car car:cars){
            System.out.println(car.getId()+" "+car.getLocation()+" "+car.getLicense_plate());
        }
    }

}
