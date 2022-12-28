package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz {
    private int id,user_id,content_id;
    private String ask,answer1,answer2,answer3,answer4;
    private User user;

    public Quiz(int id, int user_id, int content_id, String ask, String answer1, String answer2, String answer3, String answer4) {
        this.id = id;
        this.user_id = user_id;
        this.content_id = content_id;
        this.ask = ask;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }
    public Quiz(User user){
        this.user = user;
    }

    public static ArrayList<Quiz> getList(int ID){
        String sql = "SELECT * FROM quiz WHERE user_id = ?";
        ArrayList<Quiz> quizArrayList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,ID);
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
        return quizArrayList;
    }

    public static boolean add(String ask,String answer1,String answer2,String answer3,String answer4,int content_id,int user_id){
        String sql = "INSERT INTO quiz (ask,answer1,answer2,answer3,answer4,content_id,user_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setString(1,ask);
            preparedStatement.setString(2,answer1);
            preparedStatement.setString(3,answer2);
            preparedStatement.setString(4,answer3);
            preparedStatement.setString(5, answer4);
            preparedStatement.setInt(6,content_id);
            preparedStatement.setInt(7, user_id);
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
        String sql = "DELETE FROM quiz WHERE id = ?";
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

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}
