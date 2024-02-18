package org.example.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.models.Book;

import java.sql.*;
import java.util.ArrayList;

import static org.example.db.DBConnection.connection;

@AllArgsConstructor
@Getter
@Setter
public class BookRepository implements Repository<Book>{

    public static final BookRepository instance = getInstance();

    public static BookRepository getInstance(){
        if(instance==null){
            return new BookRepository();
        }
        return instance;
    }
    @Override
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
                int genreId = resultSet.getInt("genre_id");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                return new Book(bookId, name, author, genreId, price, quantity);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find book");
            e.printStackTrace();
        }
        return null;
    }

    @Override
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
                int genreId = resultSet.getInt("genre_id");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                books.add(new Book(bookId, name, author, genreId, price, quantity));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find books");
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Integer save(Book book){
        String sql = "INSERT INTO books(name, author, genre_id, price, quantity) values(?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getGenreId());
            stmt.setDouble(4, book.getPrice());
            stmt.setInt(5, book.getQuantity());
            int numberOfInsertedRows = stmt.executeUpdate();
            if(numberOfInsertedRows>0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void update(Book book){
        String sql = "UPDATE books SET name = ?, author = ?, genre_id = ?, price = ?, quantity = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getGenreId());
            stmt.setDouble(4, book.getPrice());
            stmt.setInt(5, book.getQuantity());
            stmt.setInt(6, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to upload book");
            e.printStackTrace();

        }

    }

    @Override
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
