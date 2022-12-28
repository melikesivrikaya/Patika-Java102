package TourismAgency.Model;

import TourismAgency.Helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Period {
    private int hotel_id;
    private LocalDate startPeriod,endPeriod;
    private int startDay,startMonth,startYear,endDay,endMonth,endYear;

    public Period(int hotel_id, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        this.hotel_id = hotel_id;
        this.startPeriod = LocalDate.of(startYear, startMonth, startDay);
        this.endPeriod = LocalDate.of(endYear, endMonth, endDay);
    }
    public static boolean add(int hotel_id,int s_d,int s_m,int s_y,int e_d,int e_m,int e_y){
        String query = "INSERT INTO period (hotel_id,s_day,s_month,s_year,e_day,e_month,e_year) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = Helper.getConnection().prepareStatement(query);
            ps.setInt(1,hotel_id);
            ps.setInt(2,s_d);
            ps.setInt(3,s_m);
            ps.setInt(4,s_y);
            ps.setInt(5,e_d);
            ps.setInt(6,e_m);
            ps.setInt(7,e_y);
            int x = ps.executeUpdate();
            ps.close();
            Helper.connectionClose();
            return x  != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean delete(int hotel_id){
        String query = "DELETE FROM period WHERE hotel_id = ? ";
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

    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDate startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDate getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDate endPeriod) {
        this.endPeriod = endPeriod;
    }
}
