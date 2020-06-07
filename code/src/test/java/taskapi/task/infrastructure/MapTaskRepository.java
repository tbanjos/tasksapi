package taskapi.task.infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import taskapi.task.domain.Task;
import taskapi.task.domain.TaskRepository;

public class MapTaskRepository implements TaskRepository {
    Map<String, Task> tasks = new LinkedHashMap<>();

    @Override
    public List<Task> getAll(){
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void add(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public Optional<Task> get(String id) {
        return Optional.of(tasks.get(id));
    }
    @Override
    public void update(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void delete(String id) {
        tasks.remove(id);
    }
}
