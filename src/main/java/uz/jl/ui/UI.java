package uz.jl.ui;

import uz.jl.exceptions.ApiRuntimeException;
import uz.jl.services.users.UserService;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

/**
 * @author D4uranbek ср. 20:08. 26.01.2022
 */
public class UI {
    private final UserService userService;
    private final QuizService quizService;
//    private final QuestionService questionService;
//    private final VariantService variantService;

    public UI(UserService userService, QuizService quizService, QuestionService questionService, VariantService variantService) {
        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.variantService = variantService;
    }

    /**
     * auth ui
     */
    public void login() {
        try {
            String username = Input.getStr("username ");
            String password = Input.getStr("password ");
            userService.login(username, password);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    public void logout() {
        userService.logout();
        Print.println(Color.BLUE, "Bye...");
    }

    public void userCreate() {
        try {
            String username = Input.getStr("username ");
            String password = Input.getStr("password ");
            userService.login(username, password);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    public void loginAsStudent() {
        String username = "hello";
        String password = "@helloHello007_";
        userService.login(username, password);
    }


    private <T> void showResponse(String color, T response) {
        Print.println(color, response);
    }

    private <T> void showResponse(T response) {
        showResponse(Color.RED, response);
    }


}
