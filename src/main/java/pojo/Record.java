package pojo;

public class Record {
    private String user_name;       //用户姓名
    private String user_idcard;     //用户身份证
    private String car_lic;         //车牌

    private String Rent_time;     //汽车租用时间
    private String Retu;         //归还时间
    private boolean finish;         //归还情况
    private double pay;              //支付金额

    /*
     基本方法
     */
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_idcard() {
        return user_idcard;
    }

    public void setUser_idcard(String user_idcard) {
        this.user_idcard = user_idcard;
    }

    public String getCar_lic() {
        return car_lic;
    }

    public void setCar_lic(String car_lic) {
        this.car_lic = car_lic;
    }


    public String getrent_time() {
        if(Rent_time==null)
            return null;
        else{
            String str=new String (Rent_time);
            return str.substring(0,str.length()-2);
        }
    }

    public void setCar_rent(String rent_time) {
        this.Rent_time = rent_time;
    }

    public String getRetu() {
        if(Retu==null)
            return null;
        else{
            String str=new String (Retu);
            return str.substring(0,str.length()-2);
        }

    }

    public void setRetu(String retu) {
        this.Retu = retu;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(float pay) {
        this.pay = pay;}

    /**
     * 构造函数
     * @param id   不赋值 用于占位，对应数据库中的主键
     * @param user_name     用户姓名
     * @param user_idcard   用户身份证
     * @param car_lic   车牌号
     * @param rent_time 借车时间
     * @param retu  还车时间
     * @param finish    是否还车
     * @param pay   支付
     */
    public Record(int id, String user_name, String user_idcard, String car_lic, String rent_time, String retu, Integer finish, double pay) {

        this.user_name = user_name;
        this.user_idcard = user_idcard;
        this.car_lic = car_lic;


        this.Rent_time = rent_time;

        this.Retu =  retu;

        if(finish.intValue()!=0)
            this.finish=true;
        else
            this.finish=false;
        this.pay = pay;
    }

    public Record(){


    }

}
