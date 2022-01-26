package uz.jl.ui;

import uz.jl.enums.Role.Role;
import uz.jl.utils.Print;

import java.util.Objects;

import static uz.jl.security.SecurityHolder.session;

/**
 * @author D4uranbek ср. 20:08. 26.01.2022
 */
public class Menu {
    public static void show() {
        if (Objects.isNull(session)) {
            Print.println("LOGIN");
            Print.println("REGISTER");
        } else if (session.getRole().equals(Role.ADMIN)) {
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");

        } else if (session.getRole().equals(Role.TEACHER)) {
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");

        } else if (session.getRole().equals(Role.STUDENT)) {
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");
            Print.println("");

        }

        Print.println("EXIT");
    }
}
