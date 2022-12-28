package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Educater{
    private static User user;
    public Educater(User user) {
        this.user = user;


    }
    public ArrayList<Course> getList(){
        ArrayList<Course> courseArrayList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM course WHERE user_id = ?";
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");
                courseArrayList.add(new Course(id,user_id,patika_id,lang,name));
            }
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return courseArrayList;
    }

    public String getCourseName(int course_id) {
        String name = null;
        for(Course c : getList()){
            if(c.getId() == course_id){
                name = c.getName();
            }
        }
        return name;
    }

    public  int getCourseName(String c_name) {
        int id = 0;
        for(Course c : getList()){
            if(c.getName().equals(c_name)){
                id = c.getId();
            }
        }
        return id;
    }

    public ArrayList<Course> sherchCourse(String course_name) {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        String ID = String.valueOf(user.getId());
        String sql = "SELECT * FROM course WHERE user_id = '{{id}}' AND name ILIKE '%{{name}}%' ";
        sql = sql.replace("{{id}}",ID);
        sql = sql.replace("{{name}}", course_name);
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");
                courseArrayList.add(new Course(id,user_id,patika_id,lang,name));
            }
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return courseArrayList;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
