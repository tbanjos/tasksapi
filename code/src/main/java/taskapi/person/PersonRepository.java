package taskapi.person;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
class PersonRepository {
    protected Map<String, Person> persons = new LinkedHashMap<>();

    List<Person> getAll(){
        return new ArrayList<>(persons.values());
    }

    void add(Person person) {
        persons.put(person.getId(), person);
    }

    Person get(String id) {
        return persons.get(id);
    }

    void update(Person person) {
        persons.put(person.getId(), person);
    }

    void delete(String id) {
        persons.remove(id);
    }
}
