package taskapi.assignment;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AssignmentRepositoryTest {

    AssignmentRepository repository = new AssignmentRepository();

    @Test
    public void add() {
        String personId = "1";
        String taskId = "1";
        repository.add(new Assignment(personId, taskId));
        assertThat(repository.assignments.size(), is(1));
        assertThat(repository.assignments.get(personId).get(0), is(taskId));
    }

    @Test
    public void getAllByPerson() {
        String personId1 = "1";
        String taskId1 = "1";
        String taskId2 = "2";
        repository.assignments.put(personId1, Arrays.asList(taskId1, taskId2));

        List<Assignment> assignments = repository.getAllByPerson(personId1);
        assertThat(assignments.size(), is(2));
        assertThat(assignments.get(0), is(new Assignment(personId1, taskId1)));
        assertThat(assignments.get(1), is(new Assignment(personId1, taskId2)));
    }

    @Test
    public void getAll() {
        String personId1 = "1";
        String personId2 = "2";
        String taskId1 = "1";
        String taskId2 = "2";
        repository.assignments.put(personId1, Arrays.asList(taskId1, taskId2));
        repository.assignments.put(personId2, Collections.singletonList(taskId1));

        List<Assignment> assignments = repository.getAll();
        assertThat(assignments.size(), is(3));
        assertThat(assignments.get(0), is(new Assignment(personId1, taskId1)));
        assertThat(assignments.get(1), is(new Assignment(personId1, taskId2)));
        assertThat(assignments.get(2), is(new Assignment(personId2, taskId1)));
    }
}