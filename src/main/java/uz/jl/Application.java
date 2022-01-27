package uz.jl;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.services.question.QuestionService;
import uz.jl.ui.Menu;
import uz.jl.ui.UI;
import uz.jl.ui.question.QuestionUI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.Locale;

public class Application {
    private static final UI ui = ApplicationContextHolder.getBean(UI.class);
    private static final QuestionUI questionUI = ApplicationContextHolder.getBean(QuestionUI.class);


    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Menu.show();

        String choice = Input.getStr(">>>>").toUpperCase(Locale.ROOT);
        switch (choice) {
            case "LOGIN" -> ui.login();
            case "REGISTER" -> ui.register();
            case "LOGOUT" -> ui.logout();


            case "SOLVE_QUIZ" -> ui.solveQuiz();
            case "MY_QUIZZES" -> ui.myQuizzes();

            case "CREATE_QUESTION" -> questionUI.questionCreate();
            case "DELETE_QUESTION" -> questionUI.questionDelete();
            case "1" -> ui.loginAsStudent();
//            case "2" -> ui.loginAsTeacher();
            case "3" -> questionUI.createRandomQuestion();
            case "EXIT" -> {
                Print.println(Color.GREEN, "Bye");
                return;
            }
            default -> Print.println(Color.RED, "Wrong choice");

        }
        run();
    }
}
