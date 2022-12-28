package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private int id,course_id,user_id;
    private String name,comment,link;
    private static User user;

    public Content(int id, int course_id, String name, String comment, String link,int user_id) {
        this.id = id;
        this.course_id = course_id;
        this.name = name;
        this.comment = comment;
        this.link = link;
        this.user_id = user_id;
    }
    public Content(User user){
        this.user = user;
    }

    public ArrayList<Content> getList() {
        ArrayList<Content> contentArrayList = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE user_id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contentArrayList;
    }
    public boolean update(int id,String name,String comment,String link){
        String sql = "UPDATE content SET name = '{{name}}' , comment = '{{comment}}', link = '{{link}}' WHERE id = ?";
        sql = sql.replace("{{name}}",name);
        sql = sql.replace("{{comment}}",comment);
        sql = sql.replace("{{link}}",link);
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

    public boolean add(String name, String comment, String link, int user_id,int course_id) {
        String sql = "INSERT INTO content (name,comment,link,user_id,course_id) Values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,comment);
            preparedStatement.setString(3,link);
            preparedStatement.setInt(4,user_id);
            preparedStatement.setInt(5,course_id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean delete(int id){
        String sql = "DELETE FROM content WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != 0;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public String getContentName(int content_id){
        String name = null;
        try{
            String sql = "SELECT name FROM content WHERE id = ? ";
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,content_id);
            ResultSet r = preparedStatement.executeQuery();
            r.next();
            name = r.getString("name");
            preparedStatement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return name;
    }

    public ArrayList<Content> sherch(String content_name,String course) {
        ArrayList<Content> contentArrayList = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE name ILIKE '%{{name}}%' AND user_id = ? ";
        sql = sql.replace("{{name}}",content_name);
        sql = sql.replace("?",String.valueOf(user.getId()));
        if(!course.isEmpty()){
            sql += "AND course_id = {{course_id}}";
            sql = sql.replace("{{course_id}}",course);
        }
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contentArrayList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
