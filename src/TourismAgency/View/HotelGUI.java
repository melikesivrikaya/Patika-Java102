package TourismAgency.View;

import TourismAgency.Helper;
import TourismAgency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HotelGUI extends JFrame{
    private JPanel wrapperHotel;
    private JTabbedPane room;
    private JTextField fld_hotel_name;
    private JButton btn_hotel_add;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_tel;
    private JTextField fld_hotel_star;
    private JCheckBox cb_freeParking;
    private JCheckBox cb_freeWifi;
    private JCheckBox cb_swimmingPool;
    private JCheckBox cb_fitnesCenter;
    private JCheckBox cb_hotelConcierge;
    private JCheckBox cb_SPA;
    private JCheckBox cb_roomService;
    private JCheckBox cb_ultraAllInclusive;
    private JCheckBox cb_allInclusive;
    private JCheckBox cb_roomBreakfast;
    private JCheckBox cb_fullPension;
    private JCheckBox cb_halfBoard;
    private JCheckBox cb_onlyBad;
    private JCheckBox cb_noAlcoholFullCredit;
    private JTable tbl_hotel_list;
    private JPanel panelHotel;
    private JPanel panelFacilityFeatures;
    private JPanel panelPeriod;
    private JPanel panelHotelType;
    private JTextField fld_start_day;
    private JTextField fld_end_day;
    private JTextField fld_start_month;
    private JTextField fld_start_year;
    private JTextField fld_end_month;
    private JTextField fld_end_year;
    private JPanel panelHotelList;
    private JTable tbl_room_list;
    private JTextField fld_room_m2;
    private JTextField fld_room_badp;
    private JButton btn_room_add;
    private JComboBox cmb_room_htl_id;
    private JCheckBox cb_room_tv;
    private JCheckBox cb_room_casee;
    private JCheckBox cb_room_pro;
    private JCheckBox cb_room_consol;
    private JCheckBox cb_room_minibar;
    private JTextField textField3;
    private JPanel pnl_rppm_list;
    private JPanel pnl_room_search;
    private JPanel pnl_room_add;
    private JPanel pnl_room_cb;
    private DefaultTableModel mdl_hotel_list;
    int hotel_id = 0;

    private DefaultTableModel mdl_room_list;

    public HotelGUI(){
        Helper.setLayout();
        add(wrapperHotel);
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Helper.PROJECT_TITLE);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-getSize().height)/2);
        setVisible(true);

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] hotelColums = {"ID","NAME","ADDRESS","EMAIL","TELEPHONE","STAR"};
        mdl_hotel_list.setColumnIdentifiers(hotelColums);
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(25);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        refleshHotelList();

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem facility = new JMenuItem("Facility Features Show");
        JMenuItem hostel = new JMenuItem("Hostel Type Show");
        JMenuItem period = new JMenuItem("Period Add");
        JMenuItem roomAdd = new JMenuItem("Room Add");
        JMenuItem delete = new JMenuItem("Hotel Delete");
        jPopupMenu.add(facility);
        jPopupMenu.add(hostel);
        jPopupMenu.add(period);
        jPopupMenu.add(roomAdd);
        jPopupMenu.add(delete);
        tbl_hotel_list.setComponentPopupMenu(jPopupMenu);
        tbl_hotel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
            }
        });
        facility.addActionListener(e -> {
            FacilityFeatures ff = FacilityFeatures.getByHotelID(hotel_id);
            FacilityFeaturesGUI fGUI = new FacilityFeaturesGUI(ff);
        });
        hostel.addActionListener(e -> {
            HostelType ht = HostelType.getByHotelID(hotel_id);
            HostelTypeGUI hostelTypeGUI = new HostelTypeGUI(ht);
        });
        period.addActionListener(e -> {
            PeriodAddGUI p = new PeriodAddGUI(hotel_id);
        });
        delete.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(null,"Sure ? ","?",JOptionPane.YES_NO_OPTION) == 0){
                Hotel.delete(hotel_id);
                refleshHotelList();
            }
            else{
                JOptionPane.showMessageDialog(null,"Action canceled","!",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btn_hotel_add.addActionListener(e -> {
            if(fld_hotel_name.getText().trim().isEmpty() | fld_hotel_address.getText().trim().isEmpty() | fld_hotel_mail.getText().trim().isEmpty() | fld_hotel_tel.getText().trim().isEmpty() | fld_hotel_star.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please all fields are required.","!",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                String name = fld_hotel_name.getText();
                String address = fld_hotel_address.getText();
                String email = fld_hotel_mail.getText();
                String tel = fld_hotel_tel.getText();
                String star = fld_hotel_star.getText();
                Hotel.add(name,address,email,tel,star);
                int hotel_id = Hotel.getHotel_id(name);
                boolean freeParking = cb_freeParking.isSelected();
                boolean freeWifi = cb_freeWifi.isSelected();
                boolean swimmingPool = cb_swimmingPool.isSelected();
                boolean fitnesCenter = cb_fitnesCenter.isSelected();
                boolean hotelConcierge = cb_hotelConcierge.isSelected();
                boolean spa = cb_SPA.isSelected();
                boolean roomService = cb_roomService.isSelected();
                FacilityFeatures.add(hotel_id,freeWifi,freeParking,swimmingPool,fitnesCenter,hotelConcierge,spa,roomService);
                boolean ultraAll = cb_ultraAllInclusive.isSelected();
                boolean all = cb_allInclusive.isSelected();
                boolean room = cb_roomBreakfast.isSelected();
                boolean fullP = cb_fullPension.isSelected();
                boolean half = cb_halfBoard.isSelected();
                boolean only = cb_onlyBad.isSelected();
                boolean no = cb_noAlcoholFullCredit.isSelected();
                HostelType.add(hotel_id,ultraAll,all,room,fullP,half,only,no);
                int  s_d = Integer.parseInt(fld_start_day.getText());
                int s_m = Integer.parseInt(fld_start_month.getText());
                int s_y = Integer.parseInt(fld_start_year.getText());
                int e_d = Integer.parseInt(fld_end_day.getText());
                int e_m = Integer.parseInt(fld_end_month.getText());
                int e_y = Integer.parseInt(fld_end_year.getText());
                Period.add(hotel_id,s_d,s_m,s_y,e_d,e_m,e_y);
                refleshHotelList();
                hotelSelectClear();
            }
        });


        // Room
        mdl_room_list = new DefaultTableModel();
        Object[] roomColum = {"ID","M2","BAD PIECE","STOK","TV","MINIBAR","CONSOL","PROJECTION","CASEE"};
        mdl_room_list.setColumnIdentifiers(roomColum);
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(25);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        refleshRoomList();

    }

    public static void main(String[] args) {
        HotelGUI h = new HotelGUI();
    }
    public void refleshHotelList(){
        mdl_hotel_list.setRowCount(0);
        for(Hotel h : Hotel.getList()){
            Object[] o = {h.getId(),h.getName(),h.getAddress(),h.getEmail(),h.getTel(),h.getStar()};
            mdl_hotel_list.addRow(o);
        }
    }
    public void hotelSelectClear(){
        fld_hotel_name.setText(null);
        fld_hotel_address.setText(null);
        fld_hotel_mail.setText(null);
        fld_hotel_tel.setText(null);
        fld_hotel_star.setText(null);

        cb_freeParking.setSelected(false);
        cb_freeWifi.setSelected(false);
        cb_swimmingPool.setSelected(false);
        cb_fitnesCenter.setSelected(false);
        cb_hotelConcierge.setSelected(false);
        cb_SPA.setSelected(false);
        cb_roomService.setSelected(false);

        cb_ultraAllInclusive.setSelected(false);
        cb_allInclusive.setSelected(false);
        cb_roomBreakfast.setSelected(false);
        cb_fullPension.setSelected(false);
        cb_halfBoard.setSelected(false);
        cb_onlyBad.setSelected(false);
        cb_noAlcoholFullCredit.setSelected(false);

        fld_start_day.setText(null);
        fld_start_month.setText(null);
        fld_start_year.setText(null);

        fld_end_day.setText(null);
        fld_end_month.setText(null);
        fld_end_year.setText(null);
    }

    private void refleshRoomList(){
        mdl_room_list.setRowCount(0);
        for (Room r : Room.getList()){
            Object[] o = {r.getHotel_id(),r.getM2(),r.getBad_piece(),r.getStok(),r.isTv(),r.isMinibar(),r.isConsol(),r.isProjection(),r.isCasee()};
            mdl_room_list.addRow(o);
        }


    }



}
