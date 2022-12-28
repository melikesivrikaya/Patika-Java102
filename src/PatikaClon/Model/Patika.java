package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Patika {
    private int id;
    private String name;
    private static ArrayList<Patika> patikaArrayList;

    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Patika(){}
    public static ArrayList<Patika> getList(){
        patikaArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            String sql = "SELECT * FROM patika";
            Patika patika;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                patika = new Patika();
                patika.setId(resultSet.getInt("id"));
                patika.setName(resultSet.getString("name"));
                patikaArrayList.add(patika);
            }
            statement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return patikaArrayList;
    }
    public static boolean add(String name){
        String sql= "INSERT INTO patika (name) VALUES (?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setString(1,name);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean update(Patika patika,String name){
        String sql = "UPDATE patika SET name  = '{{name}}' WHERE id = '{{id}}'";
        sql = sql.replace("{{name}}",name);
        sql = sql.replace("{{id}}",String.valueOf(patika.getId()));
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean delete(int id) {
        String sql = "DELETE FROM patika WHERE id = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
    public static Patika getFetch(int id){
        Patika patika = null;
        for(Patika p : Patika.getList()){
            if(p.getId() == id){
                patika = p;
            }
        }
        return patika;
    }
    public static String getPatikaName(int patika_id){
        String patikaName = null;
        for(Patika p : getList()){
            if(p.getId() == patika_id){
                patikaName = p.getName();
            }
        }
        return patikaName;
    }
    public ArrayList<Content> getContent(int course_id){
        ArrayList<Content> contentArrayList = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE course_id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,course_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String comment = resultSet.getString("comment");
                String link = resultSet.getString("link");
                int courseid = resultSet.getInt("course_id");
                int userid = resultSet.getInt("user_id");
                contentArrayList.add(new Content(id,courseid,name,comment,link,userid));
            }
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return  contentArrayList;

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
}
