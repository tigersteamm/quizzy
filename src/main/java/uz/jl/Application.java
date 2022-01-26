package uz.jl;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.ui.Menu;
import uz.jl.ui.UI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.Locale;

public class Application {
    private static final UI ui = ApplicationContextHolder.getBean(UI.class);


    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Menu.show();

        String choice = Input.getStr("Enter your choice: ").toUpperCase(Locale.ROOT);
        switch (choice) {
            case "LOGIN" -> ui.login();
            case "REGISTER" -> ui.register();
            case "LOGOUT" -> ui.logout();


            case "1" -> ui.loginAsAdmin();
            case "2" -> ui.loginAsTeacher();
            case "3" -> ui.loginAsStudent();
            case "EXIT" -> {
                Print.println(Color.GREEN, "Bye");
                return;
            }
            default -> Print.println(Color.RED, "Wrong choice");

        }
    }
}
