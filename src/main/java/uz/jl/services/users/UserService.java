package uz.jl.services.users;

import org.bson.types.ObjectId;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.entity.quiz.Quiz;
import uz.jl.mappers.UserMapper.UserMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.respository.user.UserRepository;
import uz.jl.security.SecurityHolder;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCrudService;
import uz.jl.utils.validator.UserValidator;

import java.util.List;

public class UserService extends AbstractService<UserRepository, UserMapper> implements GenericCrudService<User, UserCreateDto, UserUpdateDto, ObjectId> {

    private final UserValidator validator;

    public UserService(UserRepository repository, UserMapper mapper, UserValidator userValidator) {
        super(repository, mapper);
        this.validator = userValidator;
    }


    @Override
    public ResponseEntity<Data<ObjectId>> create(UserCreateDto createDto) {
        try {
//            validator.validOnCreate(createDto);
            User user = mapper.fromCreateDto(createDto);
            return new ResponseEntity<>(new Data<>(repository.create(user)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public ResponseEntity<Data<ObjectId>> register(UserCreateDto createDto) {
        try {
//            validator.validOnCreate(createDto);
            User user = mapper.fromCreateDto(createDto);
            return new ResponseEntity<>(new Data<>(repository.register(user)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public ResponseEntity<Data<Boolean>> login(String username, String password) {
        try {
            SecurityHolder.setSession(repository.login(username, password));
            return new ResponseEntity<>(new Data<>(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Data<>(false));
    }


    @Override
    public ResponseEntity<Data<Void>> update(UserUpdateDto dto) {

        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<User>> get(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<User>>> getList() {
        return null;
    }


    public void updateSession() {
        repository.updateSession();
    }

    public ResponseEntity<Data<List<Quiz>>> getQuizzes() {
        List<Quiz> quizzes = repository.getQuizzes();
        return new ResponseEntity<>(new Data<>(quizzes));
    }
}
