package taskapi.task.domain;

import java.util.List;

public interface TaskRepository {
    List<Task> getAll();

    void add(Task task);

    Task get(String id);

    void update(Task task);

    void delete(String id);
}
