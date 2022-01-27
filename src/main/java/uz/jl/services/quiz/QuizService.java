package uz.jl.services.quiz;

import org.bson.types.ObjectId;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.quiz.Question;
import uz.jl.entity.quiz.QuestionMark;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.quiz.Variant;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Level;
import uz.jl.enums.Subject;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.respository.question.QuestionRepository;
import uz.jl.respository.quiz.QuizRepository;
import uz.jl.security.SecurityHolder;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCrudService;
import uz.jl.utils.Input;
import uz.jl.utils.Print;
import uz.jl.utils.validator.QuizValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D4uranbek ср. 20:45. 26.01.2022
 */

public class QuizService extends AbstractService<QuizRepository, QuizMapper> implements GenericCrudService<Quiz, QuizCreateDto, QuizUpdateDto, ObjectId> {
    private final QuizValidator validator;

    public QuizService(QuizRepository repository, QuizMapper mapper, QuizValidator validator) {
        super(repository, mapper);
        this.validator = validator;
    }

    @Override
    public ResponseEntity<Data<ObjectId>> create(QuizCreateDto dto) {
        validator.validOnCreate(dto);
        Quiz quiz = mapper.fromCreateDto(dto);
        return new ResponseEntity<>(new Data<>(repository.create(quiz)));
    }

    @Override
    public ResponseEntity<Data<Void>> update(QuizUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Quiz>> get(ObjectId id) {
//        return new ResponseEntity<>(new Data<>(repository.get(id)));
        return null;
    }

    @Override
    public ResponseEntity<Data<List<Quiz>>> getList() {
        return null;
    }

    public Quiz solve(ObjectId key) {
        return repository.solve(key);

    }

    public Quiz create(String language, String subject, String level, String count) {
        Quiz quiz = Quiz.childBuilder()
                .language(Language.valueOf(language))
                .subject(Subject.valueOf(subject))
                .level(Level.valueOf(level))
                .questionsMarks(new ArrayList<>())
                .build();

        for (int i = 0; i < Integer.parseInt(count); i++) {
            Question random = ApplicationContextHolder.getBean(QuestionRepository.class)
                    .getRandom(language, subject, level);

            QuestionMark questionMark = QuestionMark.childBuilder()
                    .question(random).build();

            quiz.getQuestionsMarks().add(questionMark);
        }
        return quiz;
    }

    public void solve(Quiz quiz) {
        SecurityHolder.session.setCurrentQuiz(quiz);

        long currentTime = System.nanoTime();
        long finishTime = currentTime + quiz.getDuration() * 1000000000L;

        for (QuestionMark questionsMark : quiz.getQuestionsMarks()) {
            String correct = "";
            Print.println(questionsMark.getQuestion().getTitle());
            for (Variant variant : questionsMark.getQuestion().getVariants()) {
                Print.println(variant.getAnswer());
                if (variant.isCorrect())
                    correct = variant.getAnswer();
            }
            String answer = Input.getStr("answer ");
            for (Variant variant : questionsMark.getQuestion().getVariants()) {
                if (variant.getAnswer().equals(answer))
                    questionsMark.setChosenAnswerId(variant.getId());
            }

            if (correct.equals(answer))
                questionsMark.setRight(true);
            else
                questionsMark.setRight(false);

//                if (System.nanoTime() > finishTime)
//                    break;
        }
        quiz.setCompleted(true);
        SecurityHolder.session.getQuizzes().add(quiz);
        SecurityHolder.session.setCurrentQuiz(null);
    }
}
