package GUI.User_server;

import method.Car.Car_method;
import method.Record.Record_method;
import method.User.User_method;
import pojo.Car;
import pojo.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RentCar_Interface extends JFrame {
    String account;
    JScrollPane jPanel_Cen;
    JPanel jPanel_S;

    private DefaultTableModel dtm;
    private JTable table;
    private User_Interface uI;      //用户界面指针
    boolean rent;       //是否租借车了

    RentCar_Interface(String account,User_Interface UI){
        this.account=account;
        this.uI=UI;
    }
    public void Init(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("租借系统");
        this.setLayout(new BorderLayout());

        SetTable();
        Set_South();
        this.setVisible(true);
    }
    /**
     * 可用车辆数据的表单
     *
     */
    private void SetTable(){
        ArrayList<Car> R=Get_CouldRentCar();
        int record_num = R.size();
        String[] tableTitle={"Id","车牌号","地点"};
        String[][] tableData = new String[record_num][3];
        for (int i = 0; i < record_num; i++) {
            if(R.get(i).isRent()==0 && R.get(i).isIsnormal()!=0){
                tableData[i][0]=R.get(i).getId().toString();
                tableData[i][1]=R.get(i).getLicense_plate();
                tableData[i][2]=R.get(i).getLocation().toString();
            }
        }


        dtm = new DefaultTableModel(tableData, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
        };
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    private void Set_South(){
        jPanel_S=new JPanel();
        JButton jButton=new JButton("租用");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String pay_time=sdf.format(date);
                Timestamp d=Timestamp.valueOf(pay_time);

                if(!rent&&dtm.getValueAt(table.getSelectedRow(),0)!=null){
                    int id=Integer.valueOf(dtm.getValueAt(table.getSelectedRow(),0).toString());
                    new Car_method().Car_BeRent(id);
                    User user=new User_method().Select_ByAccount(account);
                    new Record_method().Insert_Record(account,user.getId_card(),dtm.getValueAt(table.getSelectedRow(),1).toString(),d);
                    Tip("租用成功");
                    rent=true;
                    Remove_Jpanle_Cen();
                    SetTable();//刷新表单
                    uI.Flush();

                }
                else if(rent){
                    Tip("你有一笔订单未完成");
                }

            }
        });
        jPanel_S.add(jButton);
        this.add(jPanel_S, BorderLayout.SOUTH);
        this.setVisible(true);
        this.repaint();

    }

    /**
     * 得到所有可租用车辆的集合
     * @return
     */
    public ArrayList<Car> Get_CouldRentCar(){
        ArrayList<Car>R=new Car_method().GetAll_CarData();
        ArrayList<Car>r=new ArrayList<>();
        for (int i = 0; i < R.size(); i++) {
            if(R.get(i).isRent()==0 && R.get(i).isIsnormal()==1){
                r.add(R.get(i));
            }
        }
        return r;
    }

    public void Remove_Jpanle_Cen(){
        this.remove(jPanel_Cen);
    }

    public void Tip(String message){
        JOptionPane.showMessageDialog(this, message,
                "提示", 1);
    }
}
