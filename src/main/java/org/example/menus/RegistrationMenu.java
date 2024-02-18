package org.example.menus;

import org.example.models.User;
import org.example.repositories.UserRepository;
import org.example.utils.PasswordHasher;

public class RegistrationMenu extends AbstractMenu implements Menu{


    @Override
    public void run() {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your lastname: ");
        String lastname = scanner.nextLine();
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        User user = UserRepository.instance.getUserByUsername(username);
        if(username!=null){
            User newUser = new User(0, name, lastname, username, password, 1);
            newUser.setPassword(PasswordHasher.hashPassword(password));
            UserRepository.instance.save(newUser);
            System.out.println("New user was created!");
        }else{
            System.out.println("This username already exists. Please try another one.");
        }


    }
}
