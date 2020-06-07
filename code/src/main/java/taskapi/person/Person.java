package taskapi.person;

public class Person {

  private String id;
  private String alias;

  public Person(){}

  public Person(String id, String alias) {
    this.id = id;
    this.alias = alias;
  }

  public String getId() {
    return id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

}