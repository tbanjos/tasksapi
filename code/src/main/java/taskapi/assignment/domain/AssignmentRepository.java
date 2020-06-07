package taskapi.assignment.domain;

import java.util.List;

public interface AssignmentRepository {
    void add(SingleAssignment assignment);

    Assignments getAllByPerson(String personId);

    List<Assignments> getAll();
}
