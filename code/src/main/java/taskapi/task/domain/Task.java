package taskapi.task.domain;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return id.equals(task.id) &&
            description.equals(task.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }
}