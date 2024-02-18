package org.example.menus;

import org.example.repositories.BookRepository;

import java.util.Scanner;

public abstract class AbstractMenu {
    protected Scanner scanner;

    public AbstractMenu(){
        scanner = new Scanner(System.in);
    }
    protected void printLine(){
        System.out.println("____________________________________________________________________");
    }
}
