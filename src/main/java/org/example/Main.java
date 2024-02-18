package org.example;

import org.example.config.PropertiesLoader;
import org.example.db.DBConnection;
import org.example.menus.MainMenu;
import org.example.menus.RegistrationMenu;
import org.example.repositories.OrderRepository;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        PropertiesLoader.loadProperties("application.properties");
//        Connection connection = DBConnection.getConnection();
////        MainMenu mainMenu = new MainMenu(bookRepository);
////        mainMenu.run();
////        RegistrationMenu loginMenu = new RegistrationMenu();
////        loginMenu.run();
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();

    }
}