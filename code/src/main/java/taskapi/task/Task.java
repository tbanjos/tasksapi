package taskapi.task;

public class Task {

  private String id;
  private String description;

  public Task(){}

  public Task(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}