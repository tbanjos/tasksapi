package taskapi.assignment.domain;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {
    void add(SingleAssignment assignment);

    Optional<PersonAssignments> getAllByPerson(String personId);

    List<PersonAssignments> getAll();
}
