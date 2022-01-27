package uz.jl.respository.user;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import uz.jl.criteria.GenericCriteria;
import uz.jl.dao.GenericDao;
import uz.jl.entity.auth.User;
import uz.jl.enums.Role.Role;
import uz.jl.respository.GenericCrudRepository;
import uz.jl.utils.Color;
import uz.jl.utils.Print;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class UserRepository extends GenericDao<GenericCriteria, User> implements
        GenericCrudRepository<User, ObjectId> {

    public UserRepository(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId create(User model) {
        model.setRole(Role.TEACHER);
        collection.insertOne(model);
        return model.getId();
    }

    public ObjectId register(User model) {
        model.setRole(Role.STUDENT);
        collection.insertOne(model);
        return model.getId();
    }

    public User login(String username, String password) {
        Bson findibleUserId = new Document("username", username);
        User session = collection.find(findibleUserId).first();
        if (Objects.isNull(session) || !Objects.equals(session.getPassword(), password)) {
            Print.println(Color.RED, "BAD CREDENTIALS");
        }
        return session;
    }


    @Override
    public void update(User model) {
        collection.updateOne(Filters.and(Filters.eq("_id", new ObjectId(model.getId().toHexString())), Filters.eq("role", "TEACHER")), Updates.combine(
                Updates.set("username", model.getUsername()),
                Updates.set("password", model.getFullName()),
                Updates.set("language", model.getLanguage())
        ));
    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public List<User> list() {
        List<User> users = new ArrayList<>();
        collection.find().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public Optional<User> get(ObjectId id) {
        User user = collection.find(Filters.eq("_id", id)).first();
        return Objects.isNull(user) ? Optional.empty() : Optional.of(user);
    }

    public void updateSession(User session) {

        collection.updateOne(Filters.eq("_id", session.getId()), Updates.(session));
    }
}
