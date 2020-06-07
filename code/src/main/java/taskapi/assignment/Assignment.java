package taskapi.assignment;

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
    public boolean equals(Object obj) {
        if(obj instanceof Assignment) {
            return personId.equals(((Assignment) obj).personId) &&
                    taskId.equals(((Assignment) obj).taskId);
        }
        return false;
    }
}
