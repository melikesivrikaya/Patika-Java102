package TourismAgency.Model;

import Patika.Java.development.TourismAgency.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FacilityFeatures {
    private int hotel_id;
    private boolean freeParking,freeWifi,swimmingPool,fitnessCenter,hotelConcierge,SPA,roomService;

    public FacilityFeatures(int hotel_id,boolean freeParking, boolean freeWifi, boolean swimmingPool, boolean fitnessCenter, boolean hotelConcierge, boolean SPA, boolean roomService) {
        this.hotel_id = hotel_id;
        this.freeParking = freeParking;
        this.freeWifi = freeWifi;
        this.swimmingPool = swimmingPool;
        this.fitnessCenter = fitnessCenter;
        this.hotelConcierge = hotelConcierge;
        this.SPA = SPA;
        this.roomService = roomService;
    }
    public FacilityFeatures(){}
    public static boolean add(int hotel_id,boolean free_wifi,boolean free_parking,boolean swimming_pool,boolean fitnes_center,boolean hotel_concierge,boolean spa,boolean room_service){
        String query = "INSERT INTO feature (hotel_id,free_parking,free_wifi,swimming_pool,fitnes_center,hotel_concierge,spa,room_service) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ps.setBoolean(2,free_parking);
            ps.setBoolean(3,free_wifi);
            ps.setBoolean(4,swimming_pool);
            ps.setBoolean(5,fitnes_center);
            ps.setBoolean(6,hotel_concierge);
            ps.setBoolean(7,spa);
            ps.setBoolean(8,room_service);
            int x = ps.executeUpdate();
            ps.close();
            Helper.connectionClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static FacilityFeatures getByHotelID(int hotel_id){
        FacilityFeatures ff = new FacilityFeatures();
        String query = "SELECT * FROM feature WHERE hotel_id = ?";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ff.setFitnessCenter(rs.getBoolean("fitnes_center"));
            ff.setSwimmingPool(rs.getBoolean("swimming_pool"));
            ff.setFreeWifi(rs.getBoolean("free_wifi"));
            ff.setFreeParking(rs.getBoolean("free_parking"));
            ff.setHotel_id(rs.getInt("hotel_id"));
            ff.setHotelConcierge(rs.getBoolean("hotel_concierge"));
            ff.setSPA(rs.getBoolean("spa"));
            ff.setRoomService(rs.getBoolean("room_service"));
            rs.close();
            ps.close();
            Helper.connectionClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return ff;
    }
    public static boolean delete(int hotel_id){
        String query = "DELETE FROM feature WHERE hotel_id = ? ";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ps.executeUpdate();
            ps.close();
            Helper.connectionClose();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }



    public boolean isFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }

    public boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isFitnessCenter() {
        return fitnessCenter;
    }

    public void setFitnessCenter(boolean fitnessCenter) {
        this.fitnessCenter = fitnessCenter;
    }

    public boolean isHotelConcierge() {
        return hotelConcierge;
    }

    public void setHotelConcierge(boolean hotelConcierge) {
        this.hotelConcierge = hotelConcierge;
    }

    public boolean isSPA() {
        return SPA;
    }

    public void setSPA(boolean SPA) {
        this.SPA = SPA;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }
}
