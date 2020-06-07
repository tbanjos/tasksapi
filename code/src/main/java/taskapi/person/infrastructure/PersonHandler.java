package taskapi.person.infrastructure;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taskapi.person.domain.Person;
import taskapi.person.domain.PersonRepository;

@Component
public class PersonHandler {

  private PersonRepository repository;

  public PersonHandler(PersonRepository repository){
    this.repository = repository;
  }

  Mono<ServerResponse> getAll(ServerRequest request) {
    Flux<Person> res = Flux.fromIterable(repository.getAll());
    return ok().contentType(MediaType.APPLICATION_JSON).body(res, Person.class);
  }

  Mono<ServerResponse> addOne(ServerRequest request) {
    Mono<Person> mono = request.bodyToMono(Person.class).flatMap(person -> {
      repository.add(person);
      return Mono.just(person);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Person.class);
  }

  Mono<ServerResponse> getOne(ServerRequest request) {
    final String id = request.pathVariable("id");
    System.out.println(id);
    Mono<Person> mono = Mono.justOrEmpty(repository.get(id));
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Person.class);
  }

  Mono<ServerResponse> updateOne(ServerRequest request) {
    Mono<Person> mono = request.bodyToMono(Person.class).flatMap(newPerson -> {
      final String id = request.pathVariable("id");
      Person person = repository.get(id).orElse(new Person(id, newPerson.getAlias()));
      person.setAlias(newPerson.getAlias());
      repository.update(person);
      return Mono.just(person);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Person.class);
  }

  Mono<ServerResponse> deleteOne(ServerRequest request) {
    final String id = request.pathVariable("id");
    repository.delete(id);
    return ok().build();
  }
}