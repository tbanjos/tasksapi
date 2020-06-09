package taskapi.assignment.infrastructure;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;

import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.PersonAssignments;
import taskapi.assignment.domain.SingleAssignment;
import taskapi.person.domain.Person;

@Repository
public class MongoAssignmentRepository implements AssignmentRepository {

    private MongoOperations mongoOps;

    public MongoAssignmentRepository(MongoClient mongoClient){
        mongoOps = new MongoTemplate(mongoClient, "tasksdb");
    }

    @Override
    public void add(SingleAssignment assignment) {
        mongoOps.upsert(new Query(where("id").is(assignment.getPersonId())),
                             new Update().addToSet("taskIds", assignment.getTaskId()),
                             Person.class);
    }

    @Override
    public Optional<PersonAssignments> getAllByPerson(String personId) {
        final Person person = mongoOps.findOne(new Query(where("id").is(personId)), Person.class);
        if(person == null || person.getTaskIds().isEmpty()){
            return Optional.empty();
        }
        return Optional.of(new PersonAssignments(person.getId(), person.getTaskIds()));
    }

    @Override
    public List<PersonAssignments> getAll() {
        final List<Person> persons = mongoOps.findAll(Person.class);
        return persons.stream()
            .filter(person -> !person.getTaskIds().isEmpty())
            .map(person -> new PersonAssignments(person.getId(), person.getTaskIds()))
            .collect(Collectors.toList());
    }
}
