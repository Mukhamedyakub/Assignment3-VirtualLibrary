package org.example.menus;

import org.example.models.Book;
import org.example.repositories.BookRepository;

public class EditBookMenu extends AbstractMenu implements Menu{
    public EditBookMenu(BookRepository bookRepository) {
        super(bookRepository);
    }

    @Override
    public void run() {
        int bookid;
        System.out.println("Enter book id: ");
        bookid = scanner.nextInt();
        scanner.nextLine();
        Book book = bookRepository.getById(bookid);
        if(book != null){
            System.out.println(book);
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
            book.setName(name);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setPrice(price);
            book.setQuantity(quantity);
            bookRepository.update(book);
            System.out.println("Book updated");
        }
        else{
            System.out.println("Book not found");
        }
    }
}
