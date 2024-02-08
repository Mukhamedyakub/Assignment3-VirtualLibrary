package org.example.menus;

import org.example.models.Book;
import org.example.repositories.BookRepository;

public class DeleteBookMenu extends AbstractMenu implements Menu{
    public DeleteBookMenu(BookRepository bookRepository) {
        super(bookRepository);
    }

    @Override
    public void run() {
        int bookid;
        System.out.println("Enter book id to delete: ");
        bookid = scanner.nextInt();
        Book book = bookRepository.getById(bookid);
        if(book != null){
            bookRepository.delete(bookid);
            System.out.println("Book with id " + bookid + " Deleted");
        }
        else{
            System.out.println("Book not found");
        }
    }
}
