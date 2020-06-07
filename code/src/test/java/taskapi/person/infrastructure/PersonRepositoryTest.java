package taskapi.person.infrastructure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import taskapi.person.domain.Person;
import taskapi.person.domain.PersonRepository;

public class PersonRepositoryTest {

    private PersonRepository repository = new MapPersonRepository();

    @Before
    public void setUp(){
        repository.add(new Person("1", "charlie"));
        repository.add(new Person("2", "alice"));
    }

    @Test
    public void getAll() {
        Person person1 = new Person("1", "charlie");
        Person person2 = new Person("2", "alice");

        List<Person> tasks = repository.getAll();

        assertThat(tasks.size(), is(2));
        assertThat(tasks.get(0), is(person1));
        assertThat(tasks.get(1), is(person2));
    }

    @Test
    public void add() {
        String personId = "3";
        String alias = "myAlias";

        repository.add(new Person(personId, alias));

        final Person person = repository.get(personId).get();
        assertThat(person.getId(), is(personId));
        assertThat(person.getAlias(), is(alias));
    }

    @Test
    public void get() {
        String personId = "1";
        Person expected = new Person(personId, "charlie");

        Person result = repository.get(personId).orElseThrow();

        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        String personId = "1";
        String newAlias = "newAlias";

        repository.update(new Person(personId, newAlias));

        assertThat(repository.get(personId).get().getAlias(), is(newAlias));
    }

    @Test
    public void delete() {
        String personId1 = "1";

        repository.delete(personId1);

        assertThat(repository.get(personId1), is(Optional.empty()));
    }
}