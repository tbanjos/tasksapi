package taskapi.person.infrastructure;

import org.springframework.stereotype.Repository;
import taskapi.person.domain.Person;
import taskapi.person.domain.PersonRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public
class MapPersonRepository implements PersonRepository {
    protected Map<String, Person> persons = new LinkedHashMap<>();

    @Override
    public List<Person> getAll(){
        return new ArrayList<>(persons.values());
    }

    @Override
    public void add(Person person) {
        persons.put(person.getId(), person);
    }

    @Override
    public Person get(String id) {
        return persons.get(id);
    }

    @Override
    public void update(Person person) {
        persons.put(person.getId(), person);
    }

    @Override
    public void delete(String id) {
        persons.remove(id);
    }
}
