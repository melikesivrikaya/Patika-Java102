package TourismAgency.View;

import TourismAgency.Model.Period;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeriodAddGUI extends JFrame{
    private JPanel wrapper;
    private JTextField s_d;
    private JTextField s_m;
    private JTextField s_y;
    private JButton btn_period_add;
    private JTextField e_d;
    private JTextField e_m;
    private JTextField e_y;

    public PeriodAddGUI(int hotel_id){
        add(wrapper);
        setSize(300,200);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width ) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height ) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        btn_period_add.addActionListener(e -> {
            if(s_d.getText().isEmpty() | s_m.getText().isEmpty() | s_y.getText().isEmpty() | e_d.getText().isEmpty() | e_m.getText().isEmpty() | e_d.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please all fields are required.","!",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                int sd = Integer.parseInt(s_d.getText());
                int sm = Integer.parseInt(s_m.getText());
                int sy = Integer.parseInt(s_y.getText());
                int ed = Integer.parseInt(e_d.getText());
                int em = Integer.parseInt(e_m.getText());
                int ey = Integer.parseInt(e_y.getText());
                if(Period.add(hotel_id, sd, sm, sy, ed, em, ey)){
                    dispose();
                }
            }
        });
    }
}
