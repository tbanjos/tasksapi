package taskapi.assignment.domain;

import java.util.Objects;

public class Assignment {

    private String personId;
    private String taskId;

    public Assignment(){}

    public Assignment(String personId, String taskId) {
        this.personId = personId;
        this.taskId = taskId;
    }

    public String getPersonId() {
        return personId;
    }

    public String getTaskId() {
        return taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return personId.equals(that.personId) &&
                taskId.equals(that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, taskId);
    }
}
