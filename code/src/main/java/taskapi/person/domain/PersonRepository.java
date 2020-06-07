package taskapi.person.domain;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> getAll();

    void add(Person person);

    Optional<Person> get(String id);

    void update(Person person);

    void delete(String id);
}
