package org.example.repositories;

import org.example.models.Order;
import org.example.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.db.DBConnection.connection;

public class OrderRepository implements Repository<Order>{

    public static final OrderRepository instance = getInstance();

    public static OrderRepository getInstance(){
        if(instance==null){
            return new OrderRepository();
        }
        return instance;
    }
    @Override
    public Order getById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int orderId = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String address = resultSet.getString("address");
                double totalPrice = resultSet.getDouble("total_price");
                return new Order(orderId, userId, address, totalPrice);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int orderId = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String address = resultSet.getString("address");
                double totalPrice = resultSet.getDouble("total_price");
                orders.add(new Order(orderId, userId, address, totalPrice));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find books");
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Integer save(Order order) {
        String sql = "INSERT INTO orders(user_id, address, total_price) values(?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getAddress());
            stmt.setDouble(3, order.getTotalPrice());
            int numberOfInsertedRows = stmt.executeUpdate();
            if(numberOfInsertedRows>0){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE orders SET user_id = ?, address = ?, total_price = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getAddress());
            stmt.setDouble(3, order.getTotalPrice());
            stmt.setInt(4, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE from orders WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete book");
            e.printStackTrace();
        }
    }


    public String getFullOrderDescription(Integer orderId) {
        String sql = "SELECT o.id as order_id, o.total_price, o.address, u.name as user_name, u.lastname as user_lastname, " +
                "b.name as book_name  " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.id " +
                "JOIN orders_books ob ON o.id = ob.order_id " +
                "JOIN books b ON ob.book_id = b.id " +
                "JOIN genres g ON b.genre_id = g.id " +
                "WHERE o.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                StringBuilder description = new StringBuilder();
                double totalPrice = 0.0;
                description.append("Order Details for Order ID: ").append(orderId).append("\n");
                while (rs.next()) {
                    totalPrice = rs.getDouble("total_price");
                    description.append("Book Name: ").append(rs.getString("book_name")).append("\n");
                    description.append("User Name: ").append(rs.getString("user_name")).append("\n");
                    description.append("User Lastname: ").append(rs.getString("user_lastname")).append("\n");
                    description.append("Address: ").append(rs.getString("address")).append("\n");
                }
                description.append("Total Price: ").append(totalPrice).append("\n");

                return description.toString();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching order description", e);
        }
    }

}
