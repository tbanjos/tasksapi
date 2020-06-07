package taskapi.person;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

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
    Mono<Person> mono = Mono.just(repository.get(id));
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Person.class);
  }

  Mono<ServerResponse> updateOne(ServerRequest request) {
    Mono<Person> mono = request.bodyToMono(Person.class).flatMap(newTask -> {
      final String id = request.pathVariable("id");
      Person person = repository.get(id);
      person.setAlias(newTask.getAlias());
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