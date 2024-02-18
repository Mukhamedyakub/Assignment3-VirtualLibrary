package org.example.menus;

import org.example.models.Book;
import org.example.models.Genre;
import org.example.repositories.BookRepository;
import org.example.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

public class AddBookMenu extends AbstractMenu implements Menu {


    @Override
    public void run() {
        String name = "";
        String author = "";
        int genreId = 0;
        double price = 0;
        int quantity = 0;
        System.out.println("Enter book name: ");
        name = scanner.nextLine();
        System.out.println("Enter book author: ");
        author = scanner.nextLine();
        System.out.println("Enter book genre id: ");
        printGenres();
        genreId = scanner.nextInt();
        System.out.println("Enter book price: ");
        price = scanner.nextDouble();
        System.out.println("Enter book quantity: ");
        quantity = scanner.nextInt();
        BookRepository.instance.save(new Book(0, name, author, genreId, price, quantity));
        System.out.println("New book was created");
    }

    private void printGenres(){
        List<Genre> genres = GenreRepository.instance.getAll();
        for(Genre genre:genres){
            System.out.println(genre);
        }
    }
}
