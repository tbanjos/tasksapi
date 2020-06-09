package taskapi.assignment.domain;

import java.util.List;
import java.util.Objects;

public class PersonAssignments {

    private String personId;
    private List<String> taskIds;

    public PersonAssignments(){}

    public PersonAssignments(String personId, List<String> taskIds) {
        this.personId = personId;
        this.taskIds = taskIds;
    }

    public String getPersonId() {
        return personId;
    }

    public List<String> getTaskIds() {
        return taskIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonAssignments that = (PersonAssignments) o;
        return personId.equals(that.personId) &&
                taskIds.equals(that.taskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, taskIds);
    }
}
