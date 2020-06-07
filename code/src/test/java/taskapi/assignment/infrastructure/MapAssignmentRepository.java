package taskapi.assignment.infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.Assignments;
import taskapi.assignment.domain.SingleAssignment;

public class MapAssignmentRepository implements AssignmentRepository {
    Map<String, List<String>> assignments = new LinkedHashMap<>();

    public void add(SingleAssignment assignment) {
        List<String> tasks = assignments.get(assignment.getPersonId());
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        if(tasks.contains(assignment.getTaskId())){
            return;
        }
        tasks.add(assignment.getTaskId());
        assignments.put(assignment.getPersonId(), tasks);
    }

    public Assignments getAllByPerson(String personId) {
        final List<String> tasks = assignments.get(personId);
        return new Assignments(personId, Objects.requireNonNullElseGet(tasks, ArrayList::new));
    }

    public List<Assignments> getAll() {
        final Set<String> all = assignments.keySet();
        if (all.isEmpty()) {
            return new ArrayList<>();
        }
        return all.stream()
                .map(personId -> new Assignments(personId, assignments.get(personId)))
                .collect(Collectors.toList());
    }
}
