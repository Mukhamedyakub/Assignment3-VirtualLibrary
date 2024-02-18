package org.example.repositories;

import org.example.models.Book;
import org.example.models.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.db.DBConnection.connection;

public class GenreRepository implements Repository<Genre> {
    public static final GenreRepository instance = getInstance();

    public static GenreRepository getInstance(){
        if(instance==null){
            return new GenreRepository();
        }
        return instance;
    }
    @Override
    public Genre getById(int id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        ArrayList<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               genres.add(new Genre(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find books");
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public Integer save(Genre t) {
        return null;
    }

    @Override
    public void update(Genre t) {

    }

    @Override
    public void delete(int id) {

    }
}
