package uz.jl.ui;

import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.quiz.QuestionMark;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.quiz.Variant;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Level;
import uz.jl.enums.Subject;
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

import java.util.ArrayList;
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


            QuizCreateDto dto = QuizCreateDto.childBuilder()
                    .language(String.valueOf(Language.valueOf(language)))
                    .subject(String.valueOf(Subject.valueOf(subject)))
                    .level(String.valueOf(Level.valueOf(level)))
                    .questionsMarks(new ArrayList<>())
                    .build();

            quizService.createAndSolve(dto, count);

            userService.updateSession();
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }


    public void myQuizzes() {
        try {
            ResponseEntity<Data<List<Quiz>>> quizzes = userService.getQuizzes();
            List<Quiz> list = quizzes.getData().getBody();
            for (Quiz quiz : list) {
                showQuizProperties(quiz);
            }
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    private void showQuizProperties(Quiz quiz) {
        Print.println("Subject: " + quiz.getSubject());
        Print.println("Level: " + quiz.getLevel());
        Print.println("Language: " + quiz.getLanguage());

        int correctCount = 0;
        for (QuestionMark questionsMark : quiz.getQuestionsMarks()) {
            showQuestionProperties(questionsMark);
            if (questionsMark.isRight()) {
                correctCount++;
            }
        }
        int count = quiz.getQuestionsMarks().size();

        Print.println("Result: " + correctCount + "/" + count);
    }

    private void showQuestionProperties(QuestionMark questionsMark) {
        Print.println("Question: " + questionsMark.getQuestion().getTitle());
        Print.println("Variants:");
        for (Variant variant : questionsMark.getQuestion().getVariants()) {
            if (variant.isCorrect())
                Print.println(Color.GREEN, variant.getAnswer());
            else
                Print.println(Color.PURPLE, variant.getAnswer());
        }
        Print.println("Your answer: " + questionsMark.isRight());
    }


    private <T> void showResponse(String color, T response) {
        Print.println(color, response);
    }

    private <T> void showResponse(T response) {
        showResponse(Color.RED, response);
    }

}
