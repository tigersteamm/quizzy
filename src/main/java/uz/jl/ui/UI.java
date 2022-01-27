package uz.jl.ui;

import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.entity.quiz.QuestionMark;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.quiz.Variant;
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




//            QuizCreateDto dto = QuizCreateDto.childBuilder()
//                    .subject(subject)
//                    .level(level)
//                    .language(language)
//                    .count(Integer.parseInt(count))
//                    .duration(Integer.parseInt(count) * 30)
//                    .build();
//            ResponseEntity<Data<ObjectId>> response = quizService.create(dto);
//
//            Quiz quiz = quizService.get(response.getData().getBody()).getData().getBody();
//
//            long currentTime = System.nanoTime();
//            long finishTime = currentTime + quiz.getDuration() * 1000000000L;
//
//            for (QuestionMark questionsMark : quiz.getQuestionsMarks()) {
//                String correct = "";
//                Print.println(questionsMark.getQuestion().getTitle());
//                for (Variant variant : questionsMark.getQuestion().getVariants()) {
//                    Print.println(variant.getAnswer());
//                    if (variant.isCorrect())
//                        correct = variant.getAnswer();
//                }
//                String answer = Input.getStr("answer ");
//                for (Variant variant : questionsMark.getQuestion().getVariants()) {
//                    if (variant.getAnswer().equals(answer))
//                        questionsMark.setChosenAnswerId(variant.getId());
//                }
//
//                if (correct.equals(answer))
//                    questionsMark.setRight(true);
//                else
//                    questionsMark.setRight(false);
//
//                if (System.nanoTime() > finishTime)
//                    break;
//            }
//            quiz.setCompleted(true);

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
