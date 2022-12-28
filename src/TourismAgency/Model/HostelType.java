package TourismAgency.Model;

import TourismAgency.Helper;

import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HostelType {
    private int hotel_id;
    private boolean ultraAllInclusive,allInclusive,roomBreakfast,fullPension,halfBoard,onlyBad,noAlcoholFullCredit;

    public HostelType(int hotel_id, boolean ultraAllInclusive, boolean allInclusive, boolean roomBreakfast, boolean fullPension, boolean halfBoard, boolean onlyBad, boolean noAlcoholFullCredit) {
        this.hotel_id = hotel_id;
        this.ultraAllInclusive = ultraAllInclusive;
        this.allInclusive = allInclusive;
        this.roomBreakfast = roomBreakfast;
        this.fullPension = fullPension;
        this.halfBoard = halfBoard;
        this.onlyBad = onlyBad;
        this.noAlcoholFullCredit = noAlcoholFullCredit;
    }
    public HostelType(){}
    public static boolean add(int hotel_id,boolean ultraAll,boolean all,boolean room,boolean full,boolean half,boolean only, boolean no){
        String query = "INSERT INTO type(hotel_id,ultra_all_inclusive,all_inclusive,room_breakfast,full_pension,half_board,only_bad,no_alcohol_full_credit) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ps.setBoolean(2,ultraAll);
            ps.setBoolean(3,all);
            ps.setBoolean(4,room);
            ps.setBoolean(5,full);
            ps.setBoolean(6,half);
            ps.setBoolean(7,only);
            ps.setBoolean(8,no);
            int x = ps.executeUpdate();
            ps.close();
            Helper.connectionClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static HostelType getByHotelID(int hotel_id){
        HostelType ht = new HostelType();
        String query = "SELECT * FROM type WHERE hotel_id = ? ";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ht.setAllInclusive(rs.getBoolean("all_inclusive"));
            ht.setHotel_id(rs.getInt("hotel_id"));
            ht.setFullPension(rs.getBoolean("full_pension"));
            ht.setHalfBoard(rs.getBoolean("half_board"));
            ht.setOnlyBad(rs.getBoolean("only_bad"));
            ht.setNoAlcoholFullCredit(rs.getBoolean("no_alcohol_full_credit"));
            ht.setRoomBreakfast(rs.getBoolean("room_breakfast"));
            ht.setUltraAllInclusive(rs.getBoolean("ultra_all_inclusive"));
            rs.close();
            ps.close();
            Helper.connectionClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return ht;
    }
    public static boolean delete(int hotel_id){
        String query = "DELETE FROM type WHERE hotel_id = ? ";
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
    public boolean isUltraAllInclusive() {
        return ultraAllInclusive;
    }

    public void setUltraAllInclusive(boolean ultraAllInclusive) {
        this.ultraAllInclusive = ultraAllInclusive;
    }

    public boolean isAllInclusive() {
        return allInclusive;
    }

    public void setAllInclusive(boolean allInclusive) {
        this.allInclusive = allInclusive;
    }

    public boolean isRoomBreakfast() {
        return roomBreakfast;
    }

    public void setRoomBreakfast(boolean roomBreakfast) {
        this.roomBreakfast = roomBreakfast;
    }

    public boolean isFullPension() {
        return fullPension;
    }

    public void setFullPension(boolean fullPension) {
        this.fullPension = fullPension;
    }

    public boolean isHalfBoard() {
        return halfBoard;
    }

    public void setHalfBoard(boolean halfBoard) {
        this.halfBoard = halfBoard;
    }

    public boolean isOnlyBad() {
        return onlyBad;
    }

    public void setOnlyBad(boolean onlyBad) {
        this.onlyBad = onlyBad;
    }

    public boolean isNoAlcoholFullCredit() {
        return noAlcoholFullCredit;
    }

    public void setNoAlcoholFullCredit(boolean noAlcoholFullCredit) {
        this.noAlcoholFullCredit = noAlcoholFullCredit;
    }
}
