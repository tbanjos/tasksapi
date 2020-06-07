package taskapi.task;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
class TaskRepository {
    protected Map<String, Task> tasks = new LinkedHashMap<>();

    List<Task> getAll(){
        return new ArrayList<>(tasks.values());
    }

    void add(Task task) {
        tasks.put(task.getId(), task);
    }

    Task get(String id) {
        return tasks.get(id);
    }

    void update(Task task) {
        tasks.put(task.getId(), task);
    }

    void delete(String id) {
        tasks.remove(id);
    }
}
