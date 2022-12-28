package TourismAgency.View;

import TourismAgency.Helper;
import TourismAgency.Model.HostelType;

import javax.swing.*;
import java.awt.*;

public class HostelTypeGUI extends JFrame{
    private JPanel wrapper;
    private JCheckBox cb_ultraAllInclusive;
    private JCheckBox cb_noAlcoholFullCredit;
    private JCheckBox cb_onlyBad;
    private JCheckBox cb_halfBoard;
    private JCheckBox cb_fullPension;
    private JCheckBox cb_allInclusive;
    private JCheckBox cb_roomBreakfast;
    public HostelTypeGUI(HostelType ht){
        add(wrapper);
        setVisible(true);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Helper.PROJECT_TITLE);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-getSize().height)/2);
        if(ht.isAllInclusive()){
            cb_allInclusive.setSelected(true);
        }
        if(ht.isFullPension()){
            cb_fullPension.setSelected(true);
        }
        if(ht.isHalfBoard()){
            cb_halfBoard.setSelected(true);
        }
        if(ht.isOnlyBad()){
            cb_onlyBad.setSelected(true);
        }
        if(ht.isRoomBreakfast()){
            cb_roomBreakfast.setSelected(true);
        }
        if(ht.isUltraAllInclusive()){
            cb_ultraAllInclusive.setSelected(true);
        }
        if(ht.isNoAlcoholFullCredit()){
            cb_noAlcoholFullCredit.setSelected(true);
        }
    }
}
