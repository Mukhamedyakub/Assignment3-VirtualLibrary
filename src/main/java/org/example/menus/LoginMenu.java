package org.example.menus;

import org.example.models.User;
import org.example.repositories.UserRepository;
import org.example.utils.PasswordHasher;

public class LoginMenu extends AbstractMenu implements Menu{
    @Override
    public void run() {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        User user = UserRepository.instance.getUserByUsername(username);
        if(user != null && PasswordHasher.verifyPassword(password, user.getPassword())){
            MainMenu.user = user;
        }else{
            System.out.println("Incorrect username or password");
        }

    }
}
