package org.example.menus;

import org.example.models.Book;
import org.example.models.Order;
import org.example.models.OrderBook;
import org.example.repositories.BookRepository;
import org.example.repositories.OrderBookRepository;
import org.example.repositories.OrderRepository;

import java.util.ArrayList;

public class MakeOrderMenu extends AbstractMenu implements Menu{
    @Override
    public void run() {
        printAllBooks();
        ArrayList<Integer> booksInOrder = new ArrayList<>();
        double totalPrice = 0;
        int input;
        do{
            System.out.println("1-add book to cart, 2-finish");
            input = scanner.nextInt();
            if(input==1){
                System.out.println("Enter book id you want to order");
                int bookId = scanner.nextInt();
                Book book = BookRepository.instance.getById(bookId);
                if(book == null){
                    System.out.println("Incorrect book id");
                }else{
                    totalPrice += book.getPrice();
                    booksInOrder.add(bookId);
                }
            }
        }while (input!=2);
        System.out.println("Enter address to deliver your order");
        scanner.nextLine();
        String address = scanner.nextLine();
        Integer orderId = OrderRepository.getInstance().save(new Order(0, MainMenu.user.getId(), address, totalPrice));
        System.out.println(orderId);
        if(orderId!=null){
            for(Integer bookId: booksInOrder){
                OrderBookRepository.instance.save(new OrderBook(0, orderId, bookId));
            }
        }
    }

    public void printAllBooks(){
        ArrayList<Book> books = BookRepository.instance.getAll();
        for(Book book:books){
            System.out.println(book);
        }
    }


}
