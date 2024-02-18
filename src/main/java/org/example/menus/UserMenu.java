package org.example.menus;

import org.example.models.Book;
import org.example.repositories.BookRepository;

import java.util.ArrayList;

public class UserMenu extends AbstractMenu implements Menu{

    @Override
    public void run() {
        int input;
        do{
            System.out.println("Choose option: 1-view all books, 2-make order, 0-logout");
            input = scanner.nextInt();
            switch (input){
                case 1:
                    ArrayList<Book> books = BookRepository.instance.getAll();
                    for(Book book: books){
                        System.out.println(book);
                    }
                    break;
                case 2:
                    new MakeOrderMenu().run();
                    break;
                case 0:
                    MainMenu.user = null;
                    new MainMenu().run();
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }while(input!=0 && MainMenu.user != null);
    }
}
