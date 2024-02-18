package org.example.repositories;

import org.example.models.Book;
import org.example.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.db.DBConnection.connection;

public class UserRepository implements Repository<User>{
    public static final UserRepository instance = getInstance();

    public static UserRepository getInstance(){
        if(instance==null){
            return new UserRepository();
        }
        return instance;
    }
    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int roleId = resultSet.getInt("role_id");
                return new User(userId, name, lastname, username, password, roleId);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int roleId = resultSet.getInt("role_id");
                users.add(new User(userId, name, lastname, username, password, roleId));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find users");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Integer save(User user) {
        String sql = "INSERT INTO users(name, lastname, username, password, role_id) values(?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRoleId());
            int numberOfInsertedRows = stmt.executeUpdate();
            if(numberOfInsertedRows>0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to create book");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, lastname = ?, userame = ?, password = ?, role_id = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRoleId());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to update user");
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE from users WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete user");
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username){
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                int roleId = resultSet.getInt("role_id");
                return new User(userId, name, lastname, username, password, roleId);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user");
            e.printStackTrace();
        }
        return null;
    }

}
