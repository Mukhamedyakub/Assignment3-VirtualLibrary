package org.example.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.models.Book;

import java.sql.*;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class BookRepository {
    private Connection connection;
    public Book getById(int id){
        String sql = "SELECT * FROM books WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int bookId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                return new Book(bookId, name, author, genre, price, quantity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find book");
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Book> getAll(){
        ArrayList<Book>books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int bookId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                books.add(new Book(bookId, name, author, genre, price, quantity));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find books");
            e.printStackTrace();
        }
        return books;
    }
    public void save(Book book){
        String sql = "INSERT INTO books(name, author, genre, price, quantity) values(?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setDouble(4, book.getPrice());
            stmt.setInt(5, book.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();

        }
    }
    public void update(Book book){
        String sql = "UPDATE books SET name = ?, author = ?, genre = ?, price = ?, quantity = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setDouble(4, book.getPrice());
            stmt.setInt(5, book.getQuantity());
            stmt.setInt(6, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();

        }

    }

    public void delete(int id){
        String sql = "DELETE from books WHERE id = ?";
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
