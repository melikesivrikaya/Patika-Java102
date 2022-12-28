package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Model.User;

import javax.swing.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    private JPanel panel1;
    private JPanel wrapperLogin;
    private JTextField fld_login_name;
    private JPasswordField fld_login_password;
    private JButton btn_login;
    private JLabel fld_stdnt_add;

    public LoginGUI(){
        Helper.setLayout();
        add(wrapperLogin);
        setSize(300,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocation(Helper.screenCennterPoint("x",getSize().height),Helper.screenCennterPoint("y",getSize().width));
        setResizable(false);
        setVisible(true);


        btn_login.addActionListener(e -> {
            User user = null;
            if(fld_login_name.getText().trim().isEmpty() || fld_login_password.getText().trim().isEmpty()){
                Helper.msg("null");
            }
            else {
                String uname = fld_login_name.getText();
                String pass = fld_login_password.getText();
                for (User u : User.getList()) {
                    if (u.getUname().equals(uname) && u.getPassword().equals(pass)) {
                        user = u;
                    }
                }


                if (user == null) {
                    Helper.msg("Kullanıcı Bulunamadı");
                } else {
                    dispose();
                    if (user.getType().equals("student")) {
                        StudentGUI studentGUI = new StudentGUI(user);
                    } else if (user.getType().equals("educater")) {
                        EducaterGUI educaterGUI = new EducaterGUI(user);
                    } else if (user.getType().equals("operator")) {
                        OperatorGUI operatorGUI = new OperatorGUI(user.getName());
                    }
                }
            }
        });

        fld_stdnt_add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StudentAddGUI studentAddGUI = new StudentAddGUI();
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
       LoginGUI loginGUI = new LoginGUI();
    }

}
