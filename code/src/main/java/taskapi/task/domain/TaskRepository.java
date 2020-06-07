package taskapi.task.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> getAll();

    void add(Task task);

    Optional<Task> get(String id);

    void update(Task task);

    void delete(String id);
}
