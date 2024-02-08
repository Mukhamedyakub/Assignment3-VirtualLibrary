package org.example.menus;

import org.example.models.Book;
import org.example.repositories.BookRepository;

public class AddBookMenu extends AbstractMenu implements Menu {
    public AddBookMenu(BookRepository bookRepository) {
        super(bookRepository);
    }

    @Override
    public void run() {
        String name = "";
        String author = "";
        String  genre = "";
        double price = 0;
        int quantity = 0;
        System.out.println("Enter book name: ");
        name = scanner.nextLine();
        System.out.println("Enter book author: ");
        author = scanner.nextLine();
        System.out.println("Enter book genre: ");
        genre = scanner.nextLine();
        System.out.println("Enter book price: ");
        price = scanner.nextDouble();
        System.out.println("Enter book quantity: ");
        quantity = scanner.nextInt();
        bookRepository.save(new Book(0, name, author, genre, price, quantity));
        System.out.println("New book was created");




    }
}
