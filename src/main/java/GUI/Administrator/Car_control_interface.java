package GUI.Administrator;

import method.Car.Car_method;
import pojo.Car;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 车辆管理界面
 */
public class Car_control_interface extends JFrame {
    private ArrayList<Car> records;
    private JPanel jPanel_N;    //上部容器
    private JComboBox<String> comboBox;   // 搜索类型下拉框

    private JTextField lic_text1;   //车牌搜索框1
    private JLabel lic;     //车牌搜索分隔
    private JTextField lic_text2;   //车牌搜索框2
    private JButton search_button;  //搜索按钮
    private JComboBox<String> comboBox_loca;    //停放位置搜索选择框

    private JScrollPane jPanel_Cen;     //中部容器

    private String[] comboxTitle={"所有车辆","车牌号", "被租用", "停放所在位置", "损坏维护/正常使用"};
    private String[] combo_loca={"1","2","3","4","5"};
    private String[] tableTitle={"id","车牌号", "被租用", "停放所在位置", "损坏维护/正常使用"};
    private DefaultTableModel dtm;
    private JTable table;

    private JPanel jPanel_S;    //下部容器

    private boolean isDelete;

    Car_control_interface(ArrayList<Car> R){
        records=R;
    }
    public void init(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("出租记录");
        this.setLayout(new BorderLayout());
        setNorth_Panel(0);
        search_All();
        setSouth_Panl();
        this.setVisible(true);
    }

    private void setNorth_Panel(int a){
        jPanel_N=new JPanel();
        comboBox=new JComboBox<>(comboxTitle);
        comboBox.setSelectedIndex(a);
        search_button=new JButton("搜索");
        jPanel_N.add(comboBox);
        if(a==1){
            lic_text1=new JTextField(5);
            lic=new JLabel("-");
            lic_text2=new JTextField(7);
            jPanel_N.add(lic_text1);
            jPanel_N.add(lic);
            jPanel_N.add(lic_text2);
        }
        if(a==2){
            JRadioButton c1=new JRadioButton("租用中");
            JRadioButton c2=new JRadioButton("未被租用");
            ButtonGroup group=new ButtonGroup();
            group.add(c1);
            group.add(c2);
            c1.setSelected(true);
            jPanel_N.add(c1);
            jPanel_N.add(c2);
        }
        if(a==3){
            comboBox_loca=new JComboBox<>(combo_loca);
            JLabel label = new JLabel("停放地点：");
            jPanel_N.add(label);
            jPanel_N.add(comboBox_loca);
        }
        if(a==4){
            JRadioButton c1=new JRadioButton("损坏");
            JRadioButton c2=new JRadioButton("正常");
            ButtonGroup group=new ButtonGroup();
            group.add(c1);
            group.add(c2);
            c1.setSelected(true);
            jPanel_N.add(c1);
            jPanel_N.add(c2);
        }
        if(a!=0)
            jPanel_N.add(search_button);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=comboBox.getSelectedIndex();
                Frame_remove_N(jPanel_N);
                setNorth_Panel(a);
            }
        });

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a==0){
                    Frame_remove_Cen(jPanel_Cen);
                    search_All();
                }
                if(a==1){
                    Frame_remove_Cen(jPanel_Cen);
                    search_ByCarlic(lic_text1.getText(),lic_text2.getText());
                }
                if(a==2){
                    Frame_remove_Cen(jPanel_Cen);
                    for(Component c:jPanel_N.getComponents()){
                        if(c instanceof JRadioButton&&((JRadioButton) c).isSelected()){
                            search_Byisrent(((JRadioButton) c).getText());
                        }
                    }
                }
                if(a==3){
                    Frame_remove_Cen(jPanel_Cen);
                    search_Byloca(comboBox_loca.getSelectedItem().toString());

                }
                if(a==4){
                    Frame_remove_Cen(jPanel_Cen);
                    for(Component c:jPanel_N.getComponents()){
                        if(c instanceof JRadioButton&&((JRadioButton) c).isSelected()){
                            search_Bysituation(((JRadioButton) c).getText());
                        }
                    }
                }
            }
        });
        this.add(jPanel_N,BorderLayout.NORTH);
        this.setVisible(true);
        this.repaint();
    }

    /**
     * 创建R数据的表单
     * @param R 要放入表单的数据集合
     */
    private void SetTable(ArrayList<Car>R){
        int record_num = R.size();
        String[][] tableData = new String[record_num][5];
        for (int i = 0; i < record_num; i++) {
            tableData[i][0]=R.get(i).getId().toString();
            tableData[i][1]=R.get(i).getLicense_plate();
            if(R.get(i).isRent()==1)
                tableData[i][2]="被租用";
            else
                tableData[i][2]="未被使用";
            tableData[i][3]=R.get(i).getLocation().toString();
            if(R.get(i).isIsnormal()==0)
                tableData[i][4]="损坏";
            else
                tableData[i][4]="正常";
        }

        dtm = new DefaultTableModel(tableData, tableTitle);
        table = new JTable(dtm){
            public boolean isCellEditable(int row, int column) {
                if(column==0||column==2)
                    return false;
                if("被租用".equals(tableData[row][2])&&(column==1||column==3||column==4))
                    return false;
                else
                    return true;
            }
        };
        dtm.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(e.getFirstRow());
                if(!isDelete){
                    Integer id=Integer.valueOf((String)dtm.getValueAt(e.getFirstRow(),0));
                    String lic=dtm.getValueAt(e.getFirstRow(),1).toString();

                    Integer isrent;
                    if("被租用".equals(dtm.getValueAt(e.getFirstRow(),2).toString()))
                        isrent=1;
                    else isrent=0;
                    Integer loca=Integer.valueOf(dtm.getValueAt(e.getFirstRow(),3).toString());

                    Integer sit;
                    if("损坏".equals(dtm.getValueAt(e.getFirstRow(),4).toString()))
                        sit=0;
                    else sit=1;
                    Car car = new Car(id, lic, isrent, loca, sit);
                    save_Data(car);
                }

            }
        });
        String[] str = { "1", "2","3","4","5"};
        String[] str1 = { "被租用", "未被使用"};
        String[] str2 = { "损坏", "正常"};
        JComboBox jbox= new JComboBox(str);
        JComboBox jbox1= new JComboBox(str1);
        JComboBox jbox2= new JComboBox(str2);
        TableColumnModel col=table.getColumnModel();
        col.getColumn(3).setCellEditor(new DefaultCellEditor(jbox));
        col.getColumn(2).setCellEditor(new DefaultCellEditor(jbox1));
        col.getColumn(4).setCellEditor(new DefaultCellEditor(jbox2));
        //支持滚动
        jPanel_Cen = new JScrollPane(table);
        this.add(jPanel_Cen, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }
    /**
     * 显示所有汽车数据/兼中部组件
     */
    private void search_All(){
        SetTable(records);
    }

    /**
     * 据车牌模糊搜索
     * @param car_lic1
     * @param car_lic2
     */
    private void search_ByCarlic(String car_lic1,String car_lic2){
        int record_num = records.size();
        String right="^[\u4e00-\u9fa5]?"+car_lic1+"[A-Z]?"+"-"+"[0-9]*"+car_lic2+"[0-9]*[A-Z]?$";

        ArrayList<Car> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(records.get(i).getLicense_plate().matches(right)){
                R.add(records.get(i));
            }
        }

        SetTable(R);

    }

    /**
     * 据车租用情况搜索
     * @param sit_rent
     */
    private void search_Byisrent(String sit_rent){
        int record_num = records.size();

        ArrayList<Car> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if((records.get(i).isRent()!=0)&&("租用中".equals(sit_rent))){
                R.add(records.get(i));
            }
            else if((records.get(i).isRent()!=1)&&("未被租用".equals(sit_rent))){
                R.add(records.get(i));
            }
        }
        SetTable(R);
    }

    /**
     * 据地点搜索
     * @param location  地点
     */
    private void search_Byloca(String location){
        int record_num = records.size();
        ArrayList<Car> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if(location.equals(records.get(i).getLocation().toString())){
                R.add(records.get(i));
            }
        }
        SetTable(R);
    }

    /**
     * 据车的正常/损坏情况搜索
     * @param situation
     */
    private void search_Bysituation(String situation){
        int record_num = records.size();

        ArrayList<Car> R=new ArrayList<>();
        for(int i=0;i<record_num;i++){
            if((records.get(i).isIsnormal()!=0)&&("正常".equals(situation))){
                R.add(records.get(i));
            }
            else if((records.get(i).isIsnormal()!=1)&&("损坏".equals(situation))){
                R.add(records.get(i));
            }
        }
        SetTable(R);
    }
    private void setSouth_Panl(){
        jPanel_S=new JPanel();
        String[] str={"1","2","3","4","5"};
        JLabel tip1=new JLabel("车牌号:");
        JLabel tip2=new JLabel("投放地点:");
        JTextField Carlic_Text=new JTextField(7);  //接收新增汽车车牌的文本框
        JComboBox<String> locBox=new JComboBox<>(str);   //新增汽车的投放地点
        JButton Insert=new JButton("新增");
        JButton Flash=new JButton("刷新");
        JButton Delete=new JButton("删除");
        jPanel_S.add(tip1);
        jPanel_S.add(Carlic_Text);
        jPanel_S.add(tip2);
        jPanel_S.add(locBox);
        jPanel_S.add(Insert);
        jPanel_S.add(Flash);
        jPanel_S.add(Delete);
        Insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Carlic_Text.getText()==null){
                    Error("请填写车牌号");
                }
                else if(new Car_method().select_ByLic(Carlic_Text.getText())!=null){
                    Tip("该车牌号已存在数据库中");
                }
                else if(!Check_lic(Carlic_Text.getText())){
                    Error("请填写正确的车牌号");
                }
                else{
                    Car car = new Car(0, Carlic_Text.getText(), 0, Integer.valueOf(locBox.getSelectedIndex()) + 1, 1);
                    Insert_Car(car);
                }
            }
        });     //插入按钮监听器
        Flash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame_remove_Cen(jPanel_Cen);
                Frame_remove_N(jPanel_N);
                setNorth_Panel(0);
                search_All();
            }
        });     //刷新按钮监听器
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()!=-1){
                    isDelete=true;
                    Delete_TableData();
                    isDelete=false;
                }

            }
        });     //删除按钮监听器
        this.add(jPanel_S,BorderLayout.SOUTH);
        this.setVisible(true);
        this.repaint();
    }

    /**
     * 编辑后保持数据
     */
    private void save_Data(Car car){
        new Car_method().Update_Car_ById(car);
        Update_records();
    }

    /**
     * 插入新汽车数据
     */
    private void Insert_Car(Car car){
        new Car_method().Insert_Car(car);
        Tip("添加完成");
        Update_records();
    }

    /**
     * 删除界面表单数据
     */
    private void Delete_TableData(){
        if(!"被租用".equals(dtm.getValueAt(table.getSelectedRow(),2))){       //被租用的车辆不可被删除
            int id=Integer.valueOf(dtm.getValueAt(table.getSelectedRow(),0).toString());    //得到删除车辆的id
            dtm.removeRow(table.getSelectedRow());
            Delete_DataBase_Data(id);
        }
        else {
            Tip("该车辆正被租用中");
        }

    }

    /**
     * 删除数据库中的数据
     */
    private void Delete_DataBase_Data(int id){
        new Car_method().Delete_carById(id);
        Update_records();
    }

    private void Update_records(){
        records=new Car_method().GetAll_CarData();
    }


    /**
     * 检测输入的车牌号是否正确
     */
    private boolean Check_lic(String lic){
        System.out.println(lic);
        String right= "^[\u4e00-\u9fa5]{1}[A-Z]{1}"+"-"+"[0-9]{5}[A-Z]{1}$";
        return lic.matches(right);
    }
    private void Frame_remove_Cen(JScrollPane jsp){
        this.remove(jsp);
    }
    private void Frame_remove_N(JPanel jPanel){
        this.remove(jPanel);
    }
    public void Error(String message){
        JOptionPane.showMessageDialog(this, message,
                "错误", JOptionPane.WARNING_MESSAGE);
    }
    public void Tip(String message){
        JOptionPane.showMessageDialog(this, message,
                "提示", 1);
    }

}
