package taskapi.person.infrastructure;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;

import taskapi.person.domain.Person;
import taskapi.person.domain.PersonRepository;

@Repository
public class MongoPersonRepository implements PersonRepository {
    private MongoOperations mongoOps;

    public MongoPersonRepository(MongoClient mongoClient){
        mongoOps = new MongoTemplate(mongoClient, "tasksdb");
    }

    @Override
    public List<Person> getAll() {
        return mongoOps.findAll(Person.class);
    }

    @Override
    public void add(Person person) {
        mongoOps.upsert(new Query(where("id").is(person.getId())),
                        new Update().set("alias", person.getAlias()),
                        Person.class);
    }

    @Override
    public Optional<Person> get(String id) {
        final Person person = mongoOps.findOne(new Query(where("id").is(id)), Person.class);
        if(person == null){
            return Optional.empty();
        }
        return Optional.of(person);
    }

    @Override
    public void update(Person person) {
        mongoOps.upsert(new Query(where("id").is(person.getId())),
                        new Update().set("alias", person.getAlias()),
                        Person.class);
    }

    @Override
    public void delete(String id) {
        mongoOps.remove(new Query(where("id").is(id)), Person.class);
    }
}
