package uz.jl.ui;

import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.exceptions.ApiRuntimeException;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.quiz.QuizService;
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

    public UI(UserService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
//        this.questionService = questionService;
//        this.variantService = variantService;
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

    public void register() {
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
        String username = "Student";
        String password = "Student";
        userService.login(username, password);
    }


    public void solveQuiz() {
        try {
            String language = Input.getStr("language ");
            String subject = Input.getStr("subject ");
            String level = Input.getStr("level ");
            String count = Input.getStr("count ");

            QuizCreateDto dto = QuizCreateDto.childBuilder()
                    .subject(subject)
                    .level(level)
                    .language(language)
                    .count(Integer.parseInt(count))
                    .duration(Integer.parseInt(count) * 30)
                    .build();
            ResponseEntity<Data<ObjectId>> response = quizService.create(dto);
            quizService.solve(response.getData().getBody());

        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    public void myQuizzes() {
        try {
            quizService.getList();
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }


    private <T> void showResponse(String color, T response) {
        Print.println(color, response);
    }

    private <T> void showResponse(T response) {
        showResponse(Color.RED, response);
    }

}
