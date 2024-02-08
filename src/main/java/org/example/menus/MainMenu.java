package org.example.menus;

import org.example.models.Book;
import org.example.repositories.BookRepository;

import java.util.ArrayList;

public class MainMenu extends AbstractMenu implements Menu{
    public MainMenu(BookRepository bookRepository) {
        super(bookRepository);
    }

    @Override
    public void run() {
        int input;
        do {
            System.out.println("Welcome to virtual library. Choose option(1-view all books, 2-add new book" +
                    ", 3-edit book, 4-delete book, 0-close program): ");
            input = scanner.nextInt();
            switch(input){
                case 1:
                    ArrayList<Book> books = bookRepository.getAll();
                    for(Book book: books){
                        System.out.println(book);
                    }
                    break;
                case 2:
                    new AddBookMenu(bookRepository).run();
                    break;
                case 3:
                    new EditBookMenu(bookRepository).run();
                    break;
                case 4:
                    new DeleteBookMenu(bookRepository).run();
                    break;
                case 0:
                    System.out.println("Closing program");
                    break;
                default:
                    System.out.println("Please enter correct option");
                    break;
            }
        }
        while(input != 0);

    }
}
