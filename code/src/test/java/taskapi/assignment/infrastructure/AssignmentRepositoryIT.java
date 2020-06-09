package taskapi.assignment.infrastructure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;

import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.PersonAssignments;
import taskapi.assignment.domain.SingleAssignment;
import taskapi.person.domain.Person;

public class AssignmentRepositoryIT {

    private MongoClient mongoClient = new MongoClient();
    private MongoTemplate mongoOps;

    private final AssignmentRepository repository = new MongoAssignmentRepository(mongoClient);

    @Before
    public void setUp() {
        mongoOps = new MongoTemplate(mongoClient, "tasksdb");
        if (mongoOps.collectionExists(Person.class)) {
            mongoOps.dropCollection(Person.class);
        }

        repository.add(new SingleAssignment("11", "11"));
        repository.add(new SingleAssignment("11", "22"));
    }

    @Test
    public void add() {
        final SingleAssignment toAdd = new SingleAssignment("22", "33");

        repository.add(toAdd);

        final List<String> taskIds = repository.getAllByPerson("22").get().getTaskIds();
        assertThat(taskIds.size(), is(1));
        assertThat(taskIds.get(0), is(toAdd.getTaskId()));
    }

    @Test
    public void getAllByPerson() {
        PersonAssignments assignments = repository.getAllByPerson("11").get();

        final List<String> taskIds = assignments.getTaskIds();
        assertThat(taskIds.size(), is(2));
        assertThat(taskIds.get(0), is("11"));
        assertThat(taskIds.get(1), is("22"));
    }

    @Test
    public void getAll() {
        List<PersonAssignments> assignments = repository.getAll();

        assertThat(assignments.size(), is(1));
        final PersonAssignments assignments1 = assignments.get(0);
        assertThat(assignments1.getPersonId(), is("11"));
        final List<String> taskIds = assignments1.getTaskIds();
        assertThat(taskIds.get(0), is("11"));
        assertThat(taskIds.get(1), is("22"));
    }

    @Test
    public void getAllShouldBeEmpty() {
        mongoOps.dropCollection(Person.class);
        mongoOps.upsert(new Query(where("id").is("1")),
                        new Update().set("alias", "aPersonWithoutTasks"),
                        Person.class);

        List<PersonAssignments> assignments = repository.getAll();

        assertThat(assignments.size(), is(0));
    }
}