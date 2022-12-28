package TourismAgency.Model;
import TourismAgency.Helper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int hotel_id,m2,bad_piece,stok;
    private boolean tv,minibar,consol,projection,casee;

    public Room(int hotel_id, int m2, int bad_piece, int stok, boolean tv, boolean minibar, boolean consol, boolean projection,boolean casee) {
        this.hotel_id = hotel_id;
        this.m2 = m2;
        this.bad_piece = bad_piece;
        this.stok = stok;
        this.tv = tv;
        this.minibar = minibar;
        this.consol = consol;
        this.projection = projection;
        this.casee = casee;
    }
    public Room(){}

    public static ArrayList<Room> getList(){
        ArrayList<Room> roomArrayList = new ArrayList<>();
        try {
            Statement s = Helper.getConnection().createStatement();
            String query = "SELECT * FROM room";
            ResultSet r = s.executeQuery(query);
            while (r.next()){
                int id = r.getInt("hotel_id");
                int bad_piece = r.getInt("bad_piece");
                boolean tv = r.getBoolean("tv");
                boolean minibar = r.getBoolean("minibar");
                boolean consol = r.getBoolean("consol");
                int m2 = r.getInt("m2");
                boolean casee = r.getBoolean("casee");
                boolean projection = r.getBoolean("projection");
                int stok = r.getInt("stok");
                roomArrayList.add(new Room(id,m2,bad_piece,stok,tv,minibar,consol,projection,casee));
            }
            s.close();
            Helper.connectionClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return roomArrayList;
    }

    public static boolean add(int hotel_id,int m2,int bad_piece,int stok, boolean tv,boolean minibar, boolean consol, boolean projection,boolean casee){
        String query = "INSERT INTO room(hotel_id,m2,bad_piece,stok,tv,minibar,consol ,projection, casee) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ps.setInt(2,m2);
            ps.setInt(3,bad_piece);
            ps.setInt(4,stok);
            ps.setBoolean(5,tv);
            ps.setBoolean(6,minibar);
            ps.setBoolean(7,consol);
            ps.setBoolean(8,projection);
            ps.setBoolean(9,casee);
            int x = ps.executeUpdate();
            return x != -1;
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

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getBad_piece() {
        return bad_piece;
    }

    public void setBad_piece(int bad_piece) {
        this.bad_piece = bad_piece;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isConsol() {
        return consol;
    }

    public void setConsol(boolean consol) {
        this.consol = consol;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public boolean isCasee() {
        return casee;
    }

    public void setCasee(boolean casee) {
        this.casee = casee;
    }
}
