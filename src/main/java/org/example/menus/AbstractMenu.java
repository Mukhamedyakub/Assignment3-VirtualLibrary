package org.example.menus;

import org.example.repositories.BookRepository;

import java.util.Scanner;

public abstract class AbstractMenu {
    protected Scanner scanner;
    protected BookRepository bookRepository;
    public AbstractMenu(BookRepository bookRepository){
        scanner = new Scanner(System.in);
        this.bookRepository = bookRepository;
    }
}
