package org.example.repositories;

import org.example.models.OrderBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.db.DBConnection.connection;

public class OrderBookRepository implements Repository<OrderBook> {
    public static final OrderBookRepository instance = getInstance();

    public static OrderBookRepository getInstance(){
        if(instance==null){
            return new OrderBookRepository();
        }
        return instance;
    }
    @Override
    public OrderBook getById(int id) {
        String sql = "SELECT * FROM orders_books WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int orderBookId = resultSet.getInt("id");
                int orderId = resultSet.getInt("order_id");
                int bookId = resultSet.getInt("book_id");
                return new OrderBook(orderBookId, orderId, bookId);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderBook> getAll() {
        ArrayList<OrderBook> ordersBooks = new ArrayList<>();
        String sql = "SELECT * FROM orders_books";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int orderBookId = resultSet.getInt("id");
                int order_id = resultSet.getInt("order_id");
                int book_id = resultSet.getInt("book_id");
                ordersBooks.add(new OrderBook(orderBookId, order_id, book_id));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find books");
            e.printStackTrace();
        }
        return ordersBooks;
    }

    @Override
    public Integer save(OrderBook orderBook) {
        String sql = "INSERT INTO orders_books(order_id, book_id) values(?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, orderBook.getOrder_id());
            stmt.setInt(2, orderBook.getBook_id());
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
    public void update(OrderBook orderBook) {
        String sql = "UPDATE orders_books SET book_id = ?, order_id = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, orderBook.getOrder_id());
            stmt.setInt(2, orderBook.getBook_id());
            stmt.setInt(3, orderBook.getBook_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE from orders_books WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete book");
            e.printStackTrace();
        }
    }
}
