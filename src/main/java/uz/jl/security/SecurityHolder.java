package uz.jl.security;

import lombok.Getter;
import uz.jl.entity.auth.User;

/**
 * @author D4uranbek ср. 20:10. 26.01.2022
 */

@Getter
public class SecurityHolder {
    public static User session;

    public static void setSession(User user) {
        session = user;
    }

    public static void killSession() {
        session = null;
    }
}
