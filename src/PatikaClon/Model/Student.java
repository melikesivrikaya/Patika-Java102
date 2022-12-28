package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Student {
    private User user;
    private int user_id,course_id;
    private ArrayList<Course> courses;

    public Student(User user) {
        this.user = user;
        courses = new ArrayList<>();
    }

    public Student(int user_id, int course_id) {
        this.user_id = user_id;
        this.course_id = course_id;
    }

    public ArrayList<Course> getCourseList(){
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
    public boolean addPatika(int course_id){
        String sql = "INSERT INTO student (user_id,course_id) Values (?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,course_id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deletePatika(int course_id){
        String sql = "DELETE FROM student WHERE course_id = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,course_id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public ArrayList<Student> getPatikaList(){
        ArrayList<Student> patikas = new ArrayList();
        String sql = " SELECT * FROM student WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int course_id = resultSet.getInt("course_id");
                patikas.add(new Student(user.getId(),course_id));
            }
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return patikas;
    }

    public String getNameByCourseID(int course_id) {
        String name = null;
        for (Course c : getCourseList()) {
            if (c.getId() == course_id) {
                name = c.getName();
            }
        }
        return name;
    }
    public int getCourseIDByName(String course_name) {
        int id = 0 ;
        for (Course c : getCourseList()) {
            if (c.getName().equals(course_name)) {
                id = c.getId();
            }
        }
        return id;
    }
    public boolean joinControl(int course_id){
        for(Student s : getPatikaList()){
            if(s.getCourse_id() == course_id){
                return true;
            }
        }
        return false;
    }
    public int getContentIDByName(String content_name,int course_id) {
        int id = 0;
        for(Content c : getContentList(course_id)){
            if(c.getName().equals(content_name)){
                id = c.getId();
            }
        }
        return id;
    }

    public ArrayList<Content> getContentList(int course_ID){
        String sql = "SELECT * FROM content WHERE course_id = ?";
        ArrayList<Content> contentArrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,course_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String comment = resultSet.getString("comment");
                String link = resultSet.getString("link");
                int course_id = resultSet.getInt("course_id");
                int user_id = resultSet.getInt("user_id");
                contentArrayList.add(new Content(id,course_id,name,comment,link,user_id));
            }
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return contentArrayList;
    }

    public ArrayList<Quiz> getQuizList(int content_ID){
        ArrayList<Quiz> quizArrayList = new ArrayList<>();
        String sql = "SELECT * FROM quiz where content_id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,content_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int content_id = resultSet.getInt("content_id");
                String ask = resultSet.getString("ask");
                String answer1 = resultSet.getString("answer1");
                String answer2 = resultSet.getString("answer2");
                String answer3 = resultSet.getString("answer3");
                String answer4 = resultSet.getString("answer4");
                quizArrayList.add(new Quiz(id,user_id,content_id,ask,answer1,answer2,answer3,answer4));
            }
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return  quizArrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
