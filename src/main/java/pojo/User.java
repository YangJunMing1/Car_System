package pojo;

public class User {
    private int id;
    public String name;         //姓名
    private String phone;       //手机号

    private String account;     //账号
    private String id_card;     //身份证

    /*
        基本方法
    */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User (String name,String phone,String id_card,String acc){
        setPhone(phone);
        setAccount(acc);
        setId_card(id_card);
        setName(name);
    }
}
