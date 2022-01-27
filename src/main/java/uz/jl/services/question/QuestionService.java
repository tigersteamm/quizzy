package uz.jl.services.question;

import org.bson.types.ObjectId;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.entity.quiz.Question;
import uz.jl.mappers.questionMapper.QuestionMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.respository.question.QuestionRepository;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCrudService;
import uz.jl.utils.validator.QuestionValidator;

import java.util.List;

public class QuestionService extends AbstractService<QuestionRepository, QuestionMapper> implements GenericCrudService<Question, QuestionCreateDto, QuestionUpdateDto, ObjectId> {

    private final QuestionValidator validator;

    public QuestionService(QuestionRepository repository, QuestionMapper mapper, QuestionValidator validator) {
        super(repository, mapper);
        this.validator = validator;
    }

    @Override
    public ResponseEntity<Data<ObjectId>> create(QuestionCreateDto dto) {
        try {
            validator.validOnCreate(dto);
            Question question = mapper.fromCreateDto(dto);
            return new ResponseEntity<>(new Data<>(repository.create(question)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<Void>> update(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        repository.delete(id);
//        return new ResponseEntity<>(new Data<>(void));
        return null;
    }

    @Override
    public ResponseEntity<Data<Question>> get(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<Question>>> getList() {
        return null;
    }
}
