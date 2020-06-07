package taskapi.assignment;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
class AssignmentRepository {
    protected Map<String, List<String>> assignments = new LinkedHashMap<>();

    void add(Assignment assignment) {
        List<String> tasks = assignments.get(assignment.getPersonId());
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        tasks.add(assignment.getTaskId());
        assignments.put(assignment.getPersonId(), tasks);
    }

    List<Assignment> getAllByPerson(String personId) {
        return assignments.get(personId)
                .stream()
                .map(taskId -> new Assignment(personId, taskId))
                .collect(Collectors.toList());
    }

    public List<Assignment> getAll() {
        return assignments.keySet().stream()
                .flatMap(personId -> assignments.get(personId)
                        .stream()
                        .map(taskId -> new Assignment(personId, taskId)))
                .collect(Collectors.toList());
    }
}
