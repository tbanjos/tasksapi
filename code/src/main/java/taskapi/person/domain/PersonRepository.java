package taskapi.person.domain;

import java.util.List;

public interface PersonRepository {
    List<Person> getAll();

    void add(Person person);

    Person get(String id);

    void update(Person person);

    void delete(String id);
}
