package taskapi.person.domain;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return id.equals(person.id) &&
            alias.equals(person.alias);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, alias);
  }
}