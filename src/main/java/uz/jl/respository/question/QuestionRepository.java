package uz.jl.respository.question;

import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import uz.jl.criteria.GenericCriteria;
import uz.jl.dao.GenericDao;
import uz.jl.entity.quiz.Question;
import uz.jl.respository.GenericCrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
//        collection.deleteOne().
    }

    @Override
    public List<Question> list() {
        return null;
    }

    @Override
    public Optional<Question> get(ObjectId id) {
        return Optional.empty();
    }

    public Question getRandom(String language,
                              String subject,
                              String level) {
        int count = (int) collection.countDocuments();
        int i = new Random().nextInt(count);
        return collection.find(Filters.and(
                Filters.eq("language", language),
                Filters.eq("subject", subject),
                Filters.eq("level", level)
        )).skip(i).first();
    }


}
