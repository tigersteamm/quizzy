package uz.jl.respository.quiz;

import org.bson.types.ObjectId;
import uz.jl.criteria.GenericCriteria;
import uz.jl.dao.GenericDao;
import uz.jl.entity.quiz.Quiz;
import uz.jl.respository.GenericCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author D4uranbek ср. 20:47. 26.01.2022
 */
public class QuizRepository extends GenericDao<GenericCriteria, Quiz> implements
        GenericCrudRepository<Quiz, ObjectId> {

    public QuizRepository(Class<Quiz> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId create(Quiz model) {
        return null;
    }

    @Override
    public void update(Quiz model) {

    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public List<Quiz> list() {
        return null;
    }

    @Override
    public Optional<Quiz> get(ObjectId id) {
        return Optional.empty();
    }
}
