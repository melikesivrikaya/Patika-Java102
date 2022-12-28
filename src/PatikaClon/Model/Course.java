package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id,user_id,patika_id;
    private String lang,name;

    public Course(int id, int user_id, int patika_id, String lang, String name) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.lang = lang;
        this.name = name;
    }
    public Course(){}

    public static ArrayList<Course> getList(){
        ArrayList<Course> courseArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM course");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");
                courseArrayList.add(new Course(id,user_id,patika_id,lang,name));
            }
            statement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return courseArrayList;
    }


    public static boolean add(String name,String lang,int user_id,int patika_id){
        String sql = "INSERT INTO course ( name, lang , patika_id , user_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lang);
            preparedStatement.setInt(3,patika_id);
            preparedStatement.setInt(4,user_id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean update(String id,String name,String lang){
        String sql = "UPDATE course SET name = '{{name}}' , lang = '{{lang}}' WHERE id = '{{id}}'";
        sql = sql.replace("{{name}}",name);
        sql = sql.replace("{{lang}}",lang);
        sql = sql.replace("{{id}}",id);
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
    public static boolean delete(int id){
        String sql = "DELETE FROM course WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
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
    public static void deleteByPatikaId(int patika_id){
        for(Course c : Course.getList()){
            if(c.getPatika_id() == patika_id){
                delete(c.getId());
            }
        }
    }
    public static void deleteByUserId(int user_id){
        for(Course c : Course.getList()){
            if(c.getUser_id() == user_id){
                delete(c.getId());
            }
        }
    }









    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
