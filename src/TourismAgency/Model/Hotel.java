package TourismAgency.Model;

import TourismAgency.Helper;
import TourismAgency.View.HostelTypeGUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name,address,email,tel,star;
    public Hotel(){}
    public Hotel(int id, String name, String address, String email, String tel, String star) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.star = star;
    }
    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        try {
            Statement s = Helper.getConnection().createStatement();
            String query = "SELECT * FROM hotel";
            ResultSet r = s.executeQuery(query);
            while (r.next()){
                int id = r.getInt("id");
                String name = r.getString("name");
                String address = r.getString("address");
                String email = r.getString("email");
                String tel = r.getString("tel");
                String star = r.getString("star");
                hotelArrayList.add(new Hotel(id,name,address,email,tel,star));
            }
            s.close();
            Helper.connectionClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hotelArrayList;
    }
    public static boolean add(String name,String address,String emil,String tel,String star){
        try {
            String query = "INSERT INTO hotel(name,address,email,tel,star) VALUES (?,?,?,?,?)";
            PreparedStatement p = Helper.getConnection().prepareStatement(query);
            p.setString(1,name);
            p.setString(2,address);
            p.setString(3,emil);
            p.setString(4,tel);
            p.setString(5,star);
            int c = p.executeUpdate();
            p.close();
            Helper.connectionClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static int getHotel_id(String hotel_name){
        int id = 0;
        String query = "SELECT id from hotel WHERE name = ? ";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setString(1,hotel_name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt("id");
            ps.close();
            Helper.connectionClose();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }
    public static boolean delete(int hotel_id){
        String query = "DELETE FROM hotel WHERE id = ? ";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ps.executeUpdate();
            ps.close();
            Helper.connectionClose();
            HostelType.delete(hotel_id);
            Period.delete(hotel_id);
            FacilityFeatures.delete(hotel_id);
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
