package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Model.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentAddGUI extends JFrame {
    private JPanel panel1;
    private JPanel wrapperStudentAdd;
    private JTextField fld_st_name;
    private JTextField fld_st_uname;
    private JTextField fld_st_password;
    private JButton btn_st_add;
    public StudentAddGUI(){
        add(wrapperStudentAdd);
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCennterPoint("x",getSize().height),Helper.screenCennterPoint("y",getSize().width));
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        btn_st_add.addActionListener(e -> {
            if(fld_st_name.getText().trim().isEmpty() || fld_st_password.getText().trim().isEmpty() || fld_st_uname.getText().trim().isEmpty()){
                Helper.msg("null");
            }
            else {
                String name = fld_st_name.getText();
                String uname = fld_st_uname.getText();
                String password = fld_st_password.getText();
                if(User.unameControl(uname)){
                    Helper.msg("Bu kullanıcı adı zaten mevcut !");
                }
                else {
                    if(User.add(name,uname,password,"student")){
                        fld_st_name.setText(null);
                        fld_st_uname.setText(null);
                        fld_st_password.setText(null);
                        Helper.msg("done");
                    }
                    else {
                        Helper.msg("none");
                    }
                }

            }
        });
    }


}
