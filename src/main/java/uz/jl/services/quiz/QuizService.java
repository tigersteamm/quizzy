package uz.jl.services.quiz;

import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.quiz.Quiz;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.respository.quiz.QuizRepository;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCrudService;
import uz.jl.utils.validator.QuizValidator;

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
        try {
            validator.validOnCreate(dto);
            Quiz quiz = mapper.fromCreateDto(dto);
            return new ResponseEntity<>(new Data<>(repository.create(quiz)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
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
        return null;
    }

    @Override
    public ResponseEntity<Data<List<Quiz>>> getList() {
        return null;
    }

    public Quiz solve(ObjectId key) {
        return repository.solve(key);

    }
}
