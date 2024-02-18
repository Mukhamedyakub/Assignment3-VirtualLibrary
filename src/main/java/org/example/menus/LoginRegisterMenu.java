package org.example.menus;

public class LoginRegisterMenu extends AbstractMenu implements Menu{

    @Override
    public void run() {
        int input;
        do{
            System.out.println("Choose option: 1-login, 2-register, 0-close");
            input = scanner.nextInt();
            switch (input){
                case 1:
                    new LoginMenu().run();
                    break;
                case 2:
                    new RegistrationMenu().run();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }while(input!=0 && MainMenu.user == null);
    }
}
