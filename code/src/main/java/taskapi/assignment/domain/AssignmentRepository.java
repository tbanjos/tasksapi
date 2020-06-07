package taskapi.assignment.domain;

import java.util.List;

public interface AssignmentRepository {
    void add(Assignment assignment);

    List<Assignment> getAllByPerson(String personId);

    List<Assignment> getAll();
}
