package org.example.menus;

import org.example.models.Book;
import org.example.models.User;
import org.example.repositories.BookRepository;

import java.util.ArrayList;

public class MainMenu extends AbstractMenu implements Menu{
    public static User user = null;
    @Override
    public void run() {
        int input;
        do{
            new LoginRegisterMenu().run();
        }while(user == null);

        if(user.getRoleId()==2){
            new AdminMenu().run();
            return;
        }

        if(user.getRoleId()==1){
            new UserMenu().run();
            return;
        }
    }
}
