package taskapi.person.infrastructure;

import org.junit.Before;
import org.junit.Test;
import taskapi.person.domain.Person;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class PersonRepositoryTest {

    MapPersonRepository repository = new MapPersonRepository();

    @Before
    public void setUp(){
        repository.persons.put("1", new Person("1", "charlie"));
        repository.persons.put("2", new Person("2", "alice"));
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

        assertThat(repository.persons.get(personId).getId(), is(personId));
        assertThat(repository.persons.get(personId).getAlias(), is(alias));
    }

    @Test
    public void get() {
        String personId = "1";
        Person expected = new Person(personId, "charlie");

        Person result = repository.get(personId);

        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        String personId = "1";
        String newAlias = "newAlias";

        repository.update(new Person(personId, newAlias));

        assertThat(repository.persons.get(personId).getAlias(), is(newAlias));
    }

    @Test
    public void delete() {
        String personId1 = "1";

        repository.delete(personId1);

        assertThat(repository.persons.get(personId1), nullValue());
    }
}