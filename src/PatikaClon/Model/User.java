package PatikaClon.Model;

import PatikaClon.Helper.DBConnector;
import PatikaClon.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name,uname,password,type;

    public User(int id, String name, String uname, String password, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.type = type;
    }
    public User(){}

    public static ArrayList<User> getList(){
        String sql = "select * from userr";
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            User u ;
            while (resultSet.next()){
                u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                u.setUname(resultSet.getString("uname"));
                u.setPassword(resultSet.getString("password"));
                u.setType(resultSet.getString("type"));
                userArrayList.add(u);
            }
            statement.close();
            DBConnector.connectClose();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userArrayList;
    }

    public static boolean add(String name,String uname,String password,String type){
        String sql = " INSERT INTO userr(name,uname,password,type) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,uname);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,type);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;

        }catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean delete(int id){
        String sql = "DELETE FROM userr WHERE id = ?";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int x = preparedStatement.executeUpdate();
            preparedStatement.close();
            DBConnector.connectClose();
            return x != -1;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean unameControl(String uname){
        for (User u : getList()){
            if(u.getUname().equals(uname)){
                return true;
            }
        }
        return false;
    }

    public static boolean update(String id ,String name,String uname,String password,String type){
        String sql = "UPDATE userr SET name = ? , uname = ? , password = ? , type = ? WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,uname);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,type);
            preparedStatement.setInt(5,Integer.parseInt(id));
            User user = findByUname(uname);
            if(user != null && user.getId() != Integer.parseInt(id)){
                Helper.msg("Bu kullanıcı zaten var !");
            }
            else if(!(type.equals("student") || type.equals("educater") || type.equals("operator"))){
                Helper.msg("Tip student,operator yada educater olmalı");
            }
            else {
                int x = preparedStatement.executeUpdate();
                preparedStatement.close();
                DBConnector.connectClose();
                return x != -1;
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }

    public static User findByUname(String uname){
        User user = null;
        for(User u : getList()){
            if(u.getUname().equals(uname)){
                user = u;
            }
        }
        return user;
    }

    public static ArrayList<User> searchUserList(String query) {
        ArrayList<User> userArrayList = new ArrayList<>();
        try{
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            User u ;
            while (resultSet.next()){
                u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                u.setUname(resultSet.getString("uname"));
                u.setPassword(resultSet.getString("password"));
                u.setType(resultSet.getString("type"));
                userArrayList.add(u);
            }
            statement.close();
            DBConnector.connectClose();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return userArrayList;
    }

    public static String query(String name, String uname, String type) {
        String query = " SELECT * FROM userr WHERE name LIKE '%{{name}}%' AND uname LIKE '%{{uname}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{uname}}", uname);
        if(!type.isEmpty()){
            query += " AND type = '{{type}}'";
            query = query.replace("{{type}}", type);
        }
        return query;
    }

    public static User getFetch(int id){
        User user = null;
        for(User u : User.getList()){
            if(u.getId() == id){
                user = u;
            }
        }
        return user;
    }
    public static String getEducaterName(int user_id){
        String educaterName = null;
        for(User u : getList()){
            if(u.getId() == user_id){
                educaterName = u.getName();
            }
        }
        return educaterName;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
