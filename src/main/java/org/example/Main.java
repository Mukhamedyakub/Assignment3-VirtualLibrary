package org.example;

import org.example.db.DBConnection;
import org.example.menus.MainMenu;
import org.example.models.Book;
import org.example.repositories.BookRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection("localhost", "5432", "postgres", "0000", "Library");
        Connection connection = dbConnection.getConnection();
        BookRepository bookRepository = new BookRepository(connection);
        MainMenu mainMenu = new MainMenu(bookRepository);
        mainMenu.run();
    }
}