package taskapi.person.infrastructure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import taskapi.person.domain.Person;
import taskapi.person.domain.PersonRepository;

public class PersonRepositoryIT {

    private final MongoClient mongoClient = new MongoClient();
    private PersonRepository repository = new MongoPersonRepository(mongoClient);

    @Before
    public void setUp() {
        final MongoTemplate mongoOps = new MongoTemplate(mongoClient, "tasksdb");
        if (mongoOps.collectionExists(Person.class)) {
            mongoOps.dropCollection(Person.class);
        }

        repository.add(new Person("1", "hello"));
        repository.add(new Person("2", "bye"));
    }

    @Test
    public void getAll() {
        List<Person> persons = repository.getAll();
        assertThat(persons.size(), is(2));
        assertThat(persons.get(0), is(new Person("1", "hello")));
        assertThat(persons.get(1), is(new Person("2", "bye")));
    }

    @Test
    public void add() {
        final Person newPerson = new Person("3", "so long");
        repository.add(newPerson);
        assertThat(repository.get("3").get(), is(newPerson));
    }

    @Test
    public void get() {
        assertThat(repository.get("1").get(), is(new Person("1", "hello")));
    }

    @Test
    public void update() {
        final Person updateAlias = new Person("2", "see ya");

        repository.update(updateAlias);

        assertThat(repository.get("2").get(), is(updateAlias));
    }

    @Test
    public void delete() {
        repository.delete("1");
        assertThat(repository.get("1"), is(Optional.empty()));
    }
}