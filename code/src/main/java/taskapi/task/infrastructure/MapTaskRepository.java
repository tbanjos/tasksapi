package taskapi.task.infrastructure;

import org.springframework.stereotype.Repository;
import taskapi.task.domain.Task;
import taskapi.task.domain.TaskRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MapTaskRepository implements TaskRepository {
    protected Map<String, Task> tasks = new LinkedHashMap<>();

    @Override
    public List<Task> getAll(){
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void add(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public Task get(String id) {
        return tasks.get(id);
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
