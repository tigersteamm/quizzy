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
        //questionUI.questionCreate();
        run();
    }

    private static void run() {
        Menu.show();

        String choice = Input.getStr(">>>>").toUpperCase(Locale.ROOT);
        switch (choice) {
            case "LOGIN" -> ui.login();
            case "REGISTER" -> ui.register();
            case "LOGOUT" -> ui.logout();

            case "CREATETEACHER" -> ui.userCreate();
            case "UPDATETEACHER" -> ui.userUpdate();
            case "DELETETEACHER" -> ui.userDelete();

            case "CREATE_QUESTION" -> questionUI.questionCreate();
            case "MY_QUIZZES" -> ui.myQuizzes();

            case "SOLVE_QUIZ" -> ui.solveQuiz();


            case "1" -> questionUI.createRandomQuestion();
//            case "1" -> ui.loginAsAdmin();
//            case "2" -> ui.loginAsTeacher();
            case "3" -> ui.loginAsStudent();
            case "EXIT" -> {
                Print.println(Color.GREEN, "Bye");
                return;
            }
            default -> Print.println(Color.RED, "Wrong choice");
        }
        run();
    }
}
