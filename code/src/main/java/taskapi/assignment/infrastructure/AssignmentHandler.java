package taskapi.assignment.infrastructure;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taskapi.assignment.domain.Assignment;
import taskapi.assignment.domain.AssignmentRepository;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class AssignmentHandler {

  private final AssignmentRepository repository;

  public AssignmentHandler(AssignmentRepository repository) {
    this.repository = repository;
  }

  Mono<ServerResponse> getAll(ServerRequest request) {
    Flux<Assignment> res = Flux.fromIterable(request.queryParam("personId")
            .map(repository::getAllByPerson)
            .orElseGet(repository::getAll));
    return ok().contentType(MediaType.APPLICATION_JSON).body(res, Assignment.class);
  }

  Mono<ServerResponse> addOne(ServerRequest request) {
    Mono<Assignment> mono = request.bodyToMono(Assignment.class).flatMap(assignment -> {
      repository.add(assignment);
      return Mono.just(assignment);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Assignment.class);
  }
}