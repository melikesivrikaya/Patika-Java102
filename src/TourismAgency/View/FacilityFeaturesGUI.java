package TourismAgency.View;

import TourismAgency.Helper;
import TourismAgency.Model.FacilityFeatures;

import javax.swing.*;
import java.awt.*;

public class FacilityFeaturesGUI extends JFrame {
    private JPanel wrapper;
    private JCheckBox cb_freeParking;
    private JCheckBox cb_freeWifi;
    private JCheckBox cb_swimmingPool;
    private JCheckBox cb_SPA;
    private JCheckBox cb_roomService;
    private JCheckBox cb_hotelConcierge;
    private JCheckBox cb_fitnesCenter;

    public FacilityFeaturesGUI(FacilityFeatures ff){
        add(wrapper);
        setSize(300, 300);
        setTitle(Helper.PROJECT_TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width ) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2 );
        setVisible(true);
        if(ff.isFitnessCenter()){
            cb_fitnesCenter.setSelected(true);
        }
        if(ff.isSPA()){
            cb_SPA.setSelected(true);
        }
        if(ff.isFreeParking()){
            cb_freeParking.setSelected(true);
        }
        if(ff.isRoomService()){
            cb_roomService.setSelected(true);
        }
        if(ff.isHotelConcierge()){
            cb_hotelConcierge.setSelected(true);
        }
        if(ff.isFreeWifi()){
            cb_freeWifi.setSelected(true);
        }
        if(ff.isSwimmingPool()){
            cb_swimmingPool.setSelected(true);
        }
    }

}
