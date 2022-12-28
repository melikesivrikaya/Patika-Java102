package PatikaClon.View;

import PatikaClon.Helper.Config;
import PatikaClon.Helper.Helper;
import PatikaClon.Model.Patika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatikaGUI extends JFrame{

    private JPanel wrapper;
    private JLabel lbl_patika_name;
    private JTextField fld_patika_name;
    private JButton btn_patika_update;


    public UpdatePatikaGUI(Patika patika){
        add(wrapper);
        setSize(300,150);
        setTitle(Config.PROJECT_TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCennterPoint("x",getSize().height),Helper.screenCennterPoint("y",getSize().width));
        fld_patika_name.setText(patika.getName());
        setVisible(true);

        btn_patika_update.addActionListener(e -> {
            if(fld_patika_name.getText().trim().isEmpty()){
                Helper.msg("null");
            }
            else {
                if (Patika.update(patika, fld_patika_name.getText())) {
                    Helper.msg("done");

                } else {
                    Helper.msg("none");
                }
            }
            dispose();
        });
    }

}
