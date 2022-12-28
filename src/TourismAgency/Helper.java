package TourismAgency;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    public static final String PROJECT_TITLE = "Tourism Agency";
    public static String url = "jdbc:postgresql://localhost/agency";
    public static String user = "postgres";
    public static String pass = "postgresql";
    public static Connection connection;




    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if(info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
    public static Connection getConnection() throws SQLException{
        try {
            connection = DriverManager.getConnection(url,user,pass);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void connectionClose() {
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
