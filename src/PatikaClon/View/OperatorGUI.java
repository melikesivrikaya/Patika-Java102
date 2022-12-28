package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Helper.Item;
import PatikaClon.Model.Course;
import PatikaClon.Model.Patika;
import PatikaClon.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tabbedPatikaList;
    private JLabel label_welcome;
    private JButton button_out;
    private JTable userTableList;
    private JPanel tabbetUserPane;
    private JScrollPane scrlUserList;
    private JLabel lbl_user_name;
    private JTextField fld_user_name;
    private JLabel lbl_user_uname;
    private JTextField fld_user_uname;
    private JLabel lbl_user_password;
    private JTextField fld_user_password;
    private JLabel lbl_user_type;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JLabel lbl_user_id;
    private JButton btn_user_delete;
    private JTextField fld_sh_name;
    private JTextField fld_sh_uname;
    private JComboBox cmb_sh_type;
    private JButton btn_search;
    private JTable tbl_patika_list;
    private JTable tableUserList;
    private JLabel lbl_patika_name;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course;
    private JScrollPane scl_course;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_edu;
    private JButton btn_course_add;
    private JButton btn_course_delete;
    private JLabel lbl_course_id;
    private JPopupMenu jPopupMenu;
    private DefaultTableModel defaultTableModel;

    private DefaultTableModel defaultTableModelPatika;

    private DefaultTableModel defaultTableModelCourse;


    public OperatorGUI(String operatorName){

        label_welcome.setText("Hoşgeldin : " + operatorName);

        add(wrapper);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocation(Helper.screenCennterPoint("x",getSize().width),Helper.screenCennterPoint("y",getSize().height));
        setVisible(true);
        Helper.setLayout();

        // **********************
        // Table User List
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 ){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] modelUserColum = {"ID","AD SOYAD","KULLANICI ADI","ŞİFRE","ÜYELİK TİPİ"};
        defaultTableModel.setColumnIdentifiers(modelUserColum);
        tableUserList.setModel(defaultTableModel);
        tableUserList.getTableHeader().setReorderingAllowed(false);
        tableUserList.getColumnModel().getColumn(0).setMaxWidth(25);
        refleshUserList();

        tableUserList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String id = tableUserList.getValueAt(tableUserList.getSelectedRow(),0).toString();
                fld_user_id.setText(id);
            }catch (Exception ee){}

        });

        tableUserList.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                String id = tableUserList.getValueAt(tableUserList.getSelectedRow(),0).toString();
                String name = tableUserList.getValueAt(tableUserList.getSelectedRow(),1).toString();
                String uname = tableUserList.getValueAt(tableUserList.getSelectedRow(),2).toString();
                String password = tableUserList.getValueAt(tableUserList.getSelectedRow(),3).toString();
                String type = tableUserList.getValueAt(tableUserList.getSelectedRow(),4).toString();
                if(User.update(id,name,uname,password,type)){
                    Helper.msg("done");
                    refleshCourseList();
                }
                refleshUserList();
            }
        });
        // **********************



        // Table Patika List
        defaultTableModelPatika = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 1){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] modelPatikaColum = {"ID","PATİKA ADI"};
        defaultTableModelPatika.setColumnIdentifiers(modelPatikaColum);
        tbl_patika_list.setModel(defaultTableModelPatika);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(25);
        jPopupMenu = new JPopupMenu();
        JMenuItem update = new JMenuItem("Güncelle");
        JMenuItem delete = new JMenuItem("Sil");
        jPopupMenu.add(update);
        jPopupMenu.add(delete);
        tbl_patika_list.setComponentPopupMenu(jPopupMenu);
        refleshPatikaList();

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });
        update.addActionListener(e -> {
            Patika patika = new Patika();
            patika.setId(Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString()));
            patika.setName(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),1).toString());
            UpdatePatikaGUI updatePatikaGUI = new UpdatePatikaGUI(patika);
            updatePatikaGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refleshPatikaList();
                    refleshCourseList();
                }
            });

        });
        delete.addActionListener(e -> {
            if(Helper.sure()){
                int id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
                if(Patika.delete(id)){
                    Course.deleteByPatikaId(id);
                    Helper.msg("done");
                }
                else {
                    Helper.msg("none");
                }
            }
            else {
                Helper.msg("cancel");
            }
            refleshPatikaList();

        });

        // ********************


        // Table Course List

        defaultTableModelCourse = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 4 || column == 3){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] courseColum = {"ID","Ders Adı","Programlama Dili","Patika Adı" ,"Eğitmen"};
        defaultTableModelCourse.setColumnIdentifiers(courseColum);
        tbl_course_list.setModel(defaultTableModelCourse);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(25);
        tbl_course_list.getColumnModel().getColumn(2).setMaxWidth(60);

        tbl_course_list.getModel().addTableModelListener(e -> {

            if (e.getType() == TableModelEvent.UPDATE) {
                String id = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString();
                String name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 1).toString();
                String lang = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 2).toString();
                if(name.trim().isEmpty() || lang.trim().isEmpty()){
                    Helper.msg("null");
                }
                else{
                    if (Course.update(id,name,lang)){
                        Helper.msg("done");
                    }
                    else {
                        Helper.msg("none");
                    }
                }
            }
        });

        tbl_course_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString();
                lbl_course_id.setText(id);

            }
        });

        refleshCourseList();



        btn_user_add.addActionListener(e -> {
            if(fld_user_name.getText().length() == 0 || fld_user_uname.getText().length() ==0 || fld_user_password.getText().length() == 0){
                Helper.msg("null");
            }
            else if(User.unameControl(fld_user_uname.getText())){
                Helper.msg("Bu kullanıcı adı zaten mevcut !");
            }
            else {
               if(User.add(fld_user_name.getText(),fld_user_uname.getText(),fld_user_password.getText(),cmb_user_type.getSelectedItem().toString())){
                   Helper.msg("done");
                   fld_user_name.setText(null);
                   fld_user_uname.setText(null);
                   fld_user_password.setText(null);
                   refleshUserList();
               }
               else {
                   Helper.msg("none");
               }
            }
        });


        btn_user_delete.addActionListener(e -> {
            if(Helper.sure()){
                int id = Integer.parseInt(fld_user_id.getText());
                User user = User.getFetch(id);
                if(User.delete(id)){
                    if(user.getType().equals("educater")){
                        Course.deleteByUserId(user.getId());
                    }
                    Helper.msg("done");
                    refleshUserList();
                    refleshCourseList();
                }
                else {
                    Helper.msg("none");
                }
            }
            else{
                Helper.msg("cancel");
            }
        });



        btn_search.addActionListener(e -> {
            String name = fld_sh_name.getText();
            String uname = fld_sh_uname.getText();
            String type = cmb_sh_type.getSelectedItem().toString();
            String query = User.query(name,uname,type);
            defaultTableModel.setRowCount(0);
            ArrayList<User> arrayList = User.searchUserList(query);
            for(User u : arrayList){
                Object[] o = {u.getId(),u.getName(),u.getUname(),u.getPassword(),u.getType()};
                defaultTableModel.addRow(o);
            }


        });

        btn_patika_add.addActionListener(e -> {
            if(fld_patika_name.getText().trim().isEmpty()){
                Helper.msg("null");
            }
            else{
                String name = fld_patika_name.getText();
                if (Patika.add(name)){
                    Helper.msg("done");
                    fld_patika_name.setText(null);
                }
                else {
                    Helper.msg("none");
                }
            }
            refleshPatikaList();
        });
        button_out.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
        btn_course_add.addActionListener(e -> {
            if(fld_course_name.getText().isEmpty() || fld_course_lang.getText().isEmpty()){
                Helper.msg("null");
            }
            else {
                String name = fld_course_name.getText();
                String lang = fld_course_lang.getText();
                Item itemUser = (Item) cmb_course_edu.getSelectedItem();
                Item itemPatika = (Item) cmb_course_patika.getSelectedItem();
                if(Course.add(name,lang,itemUser.getKey(),itemPatika.getKey())){
                    Helper.msg("done");
                    refleshCourseList();
                    fld_course_name.setText(null);
                    fld_course_lang.setText(null);
                }
                else {
                    Helper.msg("none");
                }
            }
        });
        btn_course_delete.addActionListener(e -> {
            int id = Integer.parseInt(lbl_course_id.getText());
            if(Helper.sure()) {
                if (Course.delete(id)) {
                    Helper.msg("done");
                } else {
                    Helper.msg("none");
                }
                refleshCourseList();
            }
            else {
                Helper.msg("cancel");
            }
        });
    }
    public void refleshUserList(){
        defaultTableModel.setRowCount(0);
        for( User u : User.getList()){
            Object[] o = {u.getId(),u.getName(),u.getUname(),u.getPassword(),u.getType()};
            defaultTableModel.addRow(o);
        }
     comboUser();
    }
    public void refleshPatikaList(){
        defaultTableModelPatika.setRowCount(0);
        for( Patika p : Patika.getList()){
            Object[] objects = {p.getId(),p.getName()};
            defaultTableModelPatika.addRow(objects);
        }
        comboPatika();
    }
    public void refleshCourseList(){
        defaultTableModelCourse.setRowCount(0);
        for (Course c : Course.getList()){
            Object[] objects = { c.getId(),c.getName(),c.getLang(),Patika.getPatikaName(c.getPatika_id()),User.getEducaterName(c.getUser_id()) };
            defaultTableModelCourse.addRow(objects);
        }
    }
    public void comboPatika() {
        cmb_course_patika.removeAllItems();
        Item item = null;
        for (Patika p : Patika.getList()) {
            int id = p.getId();
            String name = p.getName();
            cmb_course_patika.addItem(new Item(id,name));
        }
    }
    public void comboUser(){
        cmb_course_edu.removeAllItems();
        Item item = null;
        for(User u : User.getList()){
            if(u.getType().equals("educater")){
                int id = u.getId();
                String name = u.getName();
                cmb_course_edu.addItem(new Item(id,name));
            }
        }
    }

}
