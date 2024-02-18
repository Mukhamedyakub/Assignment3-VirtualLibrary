package org.example.menus;

import org.example.Main;
import org.example.models.Book;
import org.example.models.Order;
import org.example.models.User;
import org.example.repositories.BookRepository;
import org.example.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class AdminMenu extends AbstractMenu implements Menu{


    @Override
    public void run() {
        int input;
        do {
            System.out.println("Welcome Admin menu. Choose option(1-view all books, 2-add new book" +
                    ", 3-edit book, 4-delete book, 5-view orders, 0-logout): ");
            input = scanner.nextInt();
            switch(input){
                case 1:
                    ArrayList<Book> books = BookRepository.instance.getAll();
                    for(Book book: books){
                        System.out.println(book);
                    }
                    break;
                case 2:
                    new AddBookMenu().run();
                    break;
                case 3:
                    new EditBookMenu().run();
                    break;
                case 4:
                    new DeleteBookMenu().run();
                    break;
                case 5:
                    List<Order> orders = OrderRepository.instance.getAll();
                    for(Order order:orders){
                        System.out.println(OrderRepository.instance.getFullOrderDescription(order.getId()));
                        printLine();
                    }
                    break;
                case 0:
                    MainMenu.user = null;
                    new MainMenu().run();
                    break;
                default:
                    System.out.println("Please enter correct option");
                    break;
            }
        }
        while(input != 0);

    }
}
