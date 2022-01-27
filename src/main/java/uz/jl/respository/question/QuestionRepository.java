package uz.jl.respository.question;

import org.bson.types.ObjectId;
import uz.jl.criteria.GenericCriteria;
import uz.jl.dao.GenericDao;
import uz.jl.entity.quiz.Question;
import uz.jl.respository.GenericCrudRepository;
import uz.jl.ui.question.QuestionUI;

import java.util.List;
import java.util.Optional;

public class QuestionRepository extends GenericDao<GenericCriteria, Question> implements
        GenericCrudRepository<Question, ObjectId> {

    public QuestionRepository(Class<Question> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId create(Question model) {
        collection.insertOne(model);
        return model.getId();
    }

    @Override
    public void update(Question model) {

    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public List<Question> list() {
        return null;
    }

    @Override
    public Optional<Question> get(ObjectId id) {
        return Optional.empty();
    }
}
