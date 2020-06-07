package taskapi.person.infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import taskapi.person.domain.Person;
import taskapi.person.domain.PersonRepository;

public class MapPersonRepository implements PersonRepository {
    private Map<String, Person> persons = new LinkedHashMap<>();

    @Override
    public List<Person> getAll(){
        return new ArrayList<>(persons.values());
    }

    @Override
    public void add(Person person) {
        persons.put(person.getId(), person);
    }

    @Override
    public Optional<Person> get(String id) {
        final Person person = persons.get(id);
        if(person == null){
            return Optional.empty();
        }
        return Optional.of(person);
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
