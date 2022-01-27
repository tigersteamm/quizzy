package uz.jl.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import uz.jl.entity.auth.User;
import uz.jl.entity.quiz.Quiz;
import uz.jl.mappers.UserMapper.UserMapper;
import uz.jl.mappers.questionMapper.QuestionMapper;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.respository.question.QuestionRepository;
import uz.jl.respository.quiz.QuizRepository;
import uz.jl.respository.user.UserRepository;
import uz.jl.services.question.QuestionService;
import uz.jl.services.quiz.QuizService;
import uz.jl.services.users.UserService;
import uz.jl.ui.UI;
import uz.jl.ui.question.QuestionUI;
import uz.jl.utils.BaseUtils;
import uz.jl.utils.validator.QuestionValidator;
import uz.jl.utils.validator.QuizValidator;
import uz.jl.utils.validator.UserValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ApplicationContextHolder {
    private static MongoDatabase db = db();
    private static final BaseUtils utils;

    private static final UserValidator userValidator;
    private static QuestionValidator questionValidator;
    private static final QuizValidator quizValidator;

    private static final UserMapper userMapper;
    private static QuestionMapper questionMapper;
    private static final QuizMapper quizMapper;

    private static final UserRepository userRepository;
    private static  QuestionRepository questionRepository;
    private static final QuizRepository quizRepository;

    private static final UserService userService;
    private static final QuestionService questionService;
    private static final QuizService quizService;
    private static final UI ui;
    private static  QuestionUI questionUI;


    static {
        connect();
        utils = new BaseUtils();

        userValidator = new UserValidator(utils);
        quizValidator = new QuizValidator(utils);

        userMapper = new UserMapper();
        quizMapper = new QuizMapper();

        userRepository = new UserRepository(User.class);
        quizRepository = new QuizRepository(Quiz.class);

        userService = new UserService(userRepository, userMapper, userValidator);
        questionService = new QuestionService(questionRepository, questionMapper, questionValidator);
        quizService = new QuizService(quizRepository, quizMapper, quizValidator);


        ui = new UI(userService, quizService);
        questionUI = new QuestionUI(questionService);

    }

    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz.getSimpleName());
    }

    public static <T> T getBean(String beanName) {
        return switch (beanName) {

            case "MongoDatabase" -> (T) db;
            case "BaseUtils" -> (T) utils;

            case "UserValidator" -> (T) userValidator;
            case "QuizValidator" -> (T) quizValidator;

            case "UserMapper" -> (T) userMapper;
            case "QuizMapper" -> (T) quizMapper;

            case "UserRepository" -> (T) userRepository;
            case "QuizRepository" -> (T) quizRepository;

            case "UserService" -> (T) userService;
            case "QuizService" -> (T) quizService;


            case "UI" -> (T) ui;
            case "QuestionUI" -> (T) questionUI;
            default -> throw new RuntimeException("Bean Not Found");
        };
    }


    private static MongoDatabase db() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

        Logger rootLogger = Logger.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        try {
            MongoClient mongoClient = MongoClients.create(clientSettings);
            return mongoClient.getDatabase("quiz-app-b3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
