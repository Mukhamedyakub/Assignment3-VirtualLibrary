package org.example.menus;

import org.example.models.Book;
import org.example.repositories.BookRepository;

public class DeleteBookMenu extends AbstractMenu implements Menu{


    @Override
    public void run() {
        int bookid;
        System.out.println("Enter book id to delete: ");
        bookid = scanner.nextInt();
        Book book = BookRepository.instance.getById(bookid);
        if(book != null){
            BookRepository.instance.delete(bookid);
            System.out.println("Book with id " + bookid + " Deleted");
        }
        else{
            System.out.println("Book not found");
        }
    }
}
