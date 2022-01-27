package uz.jl.ui;

import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.quiz.Quiz;
import uz.jl.exceptions.ApiRuntimeException;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.security.SecurityHolder;
import uz.jl.services.question.QuestionService;
import uz.jl.services.quiz.QuizService;
import uz.jl.services.users.UserService;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;

/**
 * @author D4uranbek ср. 20:08. 26.01.2022
 */
public class UI {
    private final UserService userService;
    private final QuizService quizService;
    private final QuestionService questionService;
//    private final VariantService variantService;

    public UI(UserService userService, QuizService quizService, QuestionService questionService) {
        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
//        this.variantService = variantService;
    }


    /**
     * auth ui
     */
    //------------------------------------------AuthUI----------------------------------------------------------------------------------------
    public void register() {
        UserCreateDto dto = UserCreateDto.childBuilder().username(Input.getStr("Username: ")).password(Input.getStr("Password:")).language(Input.getStr("Language:")).build();
        ResponseEntity<Data<ObjectId>> response = userService.register(dto);
        Print.println(response.getData().getBody());
    }

    public void login() {
        String username = Input.getStr("Username: ");
        String password = Input.getStr("Password: ");
        ResponseEntity<Data<Boolean>> response = userService.login(username, password);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(SecurityHolder.session));
//        Print.println(SecurityHolder.session.getUsername() + " " + response.getData().getBody());
    }

    public void logout() {
        SecurityHolder.killSession();
    }

    public void userCreate() {
        try {
            String username = Input.getStr("username: ");
            String fullName = Input.getStr("FullName: ");
            String password = Input.getStr("password: ");
            String language = Input.getStr("language: ");
            UserCreateDto dto = new UserCreateDto(username, fullName, password, language);
            ResponseEntity<Data<ObjectId>> dataResponseEntity = userService.create(dto);
            showResponse(dataResponseEntity.getData().getBody());
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    public void userUpdate() {
        try {
            String id = Input.getStr("UserId");
            String username = Input.getStr("username: ");
            String fullName = Input.getStr("FullName: ");
            String language = Input.getStr("language: ");
            UserUpdateDto dto = new UserUpdateDto(id, username, fullName, language);
            ResponseEntity<Data<Void>> update = userService.update(dto);
            showResponse(update.getData().getBody());
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    public void userDelete() {
        try {
            String id = Input.getStr("UserId:");
            userService.delete(new ObjectId(id));
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
            String language = Input.getStr("language: ");
            String subject = Input.getStr("subject ");
            String level = Input.getStr("level ");
            String count = Input.getStr("count ");

            Quiz quiz = quizService.create(language, subject, level, count);
            quizService.solve(quiz);
            userService.updateSession(SecurityHolder.session);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }


    public void myQuizzes() {
        try {
            ResponseEntity<Data<List<Quiz>>> quizzes = userService.getQuizzes();
            Print.println(quizzes.getData().getBody());
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
