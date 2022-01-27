package uz.jl.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.entity.quiz.Question;
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
import uz.jl.ui.question.QuestionUI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.ArrayList;

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
        ResponseEntity<Data<ObjectId>> response = userService.create(dto);
        Print.println(response.getData().getBody());
    }

    public void login() {
        String username = Input.getStr("Username: ");
        String password = Input.getStr("Password: ");
        ResponseEntity<Data<Boolean>> response = userService.login(username, password);
        Print.println(SecurityHolder.session.getUsername() + " " + response.getData().getBody());
    }

    public void logout() {
        SecurityHolder.killSession();
    }

    public void userCreate() {
        try {
            String username = Input.getStr("username: ");
            String fullName=Input.getStr("FullName: ");
            String password = Input.getStr("password: ");
            String language = Input.getStr("language: ");
            UserCreateDto dto=new UserCreateDto(username,fullName,password,language);
            ResponseEntity<Data<ObjectId>> dataResponseEntity = userService.create(dto);
            showResponse(dataResponseEntity.getData().getBody());
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }
    public void userUpdate() {
        try {
            String id=Input.getStr("UserId");
            String username = Input.getStr("username: ");
            String fullName=Input.getStr("FullName: ");
            String language = Input.getStr("language: ");
            UserUpdateDto dto=new UserUpdateDto(id,username,fullName,language);
            ResponseEntity<Data<Void>> update = userService.update(dto);
            showResponse(update.getData().getBody());
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }
    public void userDelete(){
        try{
            String id=Input.getStr("UserId:");
               userService.delete(new ObjectId(id));
        }catch (ApiRuntimeException e){
            showResponse (e.getMessage());
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

            Quiz quiz = Quiz.childBuilder()
                    .language(Language.valueOf(language))
                    .subject(Subject.valueOf(subject))
                    .level(Level.valueOf(level))
                    .questionsMarks(new ArrayList<>())
                    .build();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                Question random = questionService.getRandom(language, subject, level);
                QuestionMark questionMark = QuestionMark.childBuilder()
                        .question(random).build();

                quiz.getQuestionsMarks().add(questionMark);
            }

//            SecurityHolder.session.setCurrentQuiz(quiz);

            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(quiz));


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
