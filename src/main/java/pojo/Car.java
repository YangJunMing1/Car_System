package pojo;

public class Car {
    private Integer id;
    public String license_plate;    //车牌号
    public Integer  isrent;            //是否被租用  1-被租用，0-未使用
    public Integer location;            //汽车目前停放所在站点
    public Integer isnormal;           //汽车状态  1-正常，0-损坏
          //汽车被租借记录

    /*
    基本方法
    */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer isIsrent() {
        return isrent;
    }

    public void setIsrent(Integer isrent) {
        this.isrent = isrent;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public Integer isRent() {
        return isrent;
    }

    public void setRent(Integer rent) {
        this.isrent = rent;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer isIsnormal() {
        return isnormal;
    }

    public void setIsnormal(Integer isnormal) {
        this.isnormal = isnormal;
    }



    public Car(Integer id,String pl,Integer isrent,Integer location,Integer sit){

        this.id=id;
        setLicense_plate(pl);
        this.isrent=isrent;
        setLocation(location);
        this.isnormal=sit;
    }


}
