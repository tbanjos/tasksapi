package taskapi.assignment.infrastructure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import taskapi.assignment.domain.PersonAssignments;
import taskapi.assignment.domain.SingleAssignment;

public class AssignmentRepositoryTest {

    private MapAssignmentRepository repository = new MapAssignmentRepository();

    @Test
    public void add() {
        String personId = "1";
        String taskId = "1";
        repository.add(new SingleAssignment(personId, taskId));
        assertThat(repository.assignments.size(), is(1));
        assertThat(repository.assignments.get(personId).get(0), is(taskId));
    }

    @Test
    public void getAllByPerson() {
        String personId1 = "1";
        String taskId1 = "1";
        String taskId2 = "2";
        repository.assignments.put(personId1, Arrays.asList(taskId1, taskId2));

        PersonAssignments assignments = repository.getAllByPerson(personId1).get();
        assertThat(assignments, is(new PersonAssignments(personId1, Arrays.asList(taskId1, taskId2))));
    }

    @Test
    public void getAll() {
        String personId1 = "1";
        String personId2 = "2";
        String taskId1 = "1";
        String taskId2 = "2";
        repository.assignments.put(personId1, Arrays.asList(taskId1, taskId2));
        repository.assignments.put(personId2, Collections.singletonList(taskId1));

        List<PersonAssignments> assignments = repository.getAll();
        assertThat(assignments.size(), is(2));
        assertThat(assignments.get(0), is(new PersonAssignments(personId1, Arrays.asList(taskId1, taskId2))));
        assertThat(assignments.get(1), is(new PersonAssignments(personId2, Collections.singletonList(taskId1))));
    }
}