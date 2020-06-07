package taskapi.task.infrastructure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import taskapi.task.domain.Task;

public class TaskRepositoryTest {

    MapTaskRepository repository = new MapTaskRepository();

    @Before
    public void setUp(){
        repository.tasks.put("1", new Task("1", "task 1"));
        repository.tasks.put("2", new Task("2", "task 2"));
    }

    @Test
    public void getAll() {
        Task task1 = new Task("1", "task 1");
        Task task2 = new Task("2", "task 2");

        List<Task> tasks = repository.getAll();

        assertThat(tasks.size(), is(2));
        assertThat(tasks.get(0), is(task1));
        assertThat(tasks.get(1), is(task2));
    }

    @Test
    public void add() {
        String taskId = "3";
        String description = "a task";

        repository.add(new Task(taskId, description));

        assertThat(repository.tasks.get(taskId).getId(), is(taskId));
        assertThat(repository.tasks.get(taskId).getDescription(), is(description));
    }

    @Test
    public void get() {
        Task expected = new Task("1", "task 1");

        Task result = repository.get("1").get();

        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        String taskId = "1";

        repository.update(new Task(taskId, "first task"));

        assertThat(repository.tasks.get(taskId).getDescription(), is("first task"));
    }

    @Test
    public void delete() {
        String taskId = "1";

        repository.delete(taskId);

        assertThat(repository.tasks.get(taskId), nullValue());
    }
}