package taskapi.assignment.infrastructure;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Collections;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taskapi.assignment.domain.Assignments;
import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.SingleAssignment;

@Component
public class AssignmentHandler {

  private final AssignmentRepository repository;

  public AssignmentHandler(AssignmentRepository repository) {
    this.repository = repository;
  }

  Mono<ServerResponse> getAll(ServerRequest request) {
    Flux<Assignments> res = Flux.fromIterable(request.queryParam("personId")
            .map(personId -> Collections.singletonList(repository.getAllByPerson(personId)))
            .orElseGet(repository::getAll));
    return ok().contentType(MediaType.APPLICATION_JSON).body(res, Assignments.class);
  }

  Mono<ServerResponse> addOne(ServerRequest request) {
    Mono<SingleAssignment> mono = request.bodyToMono(SingleAssignment.class).flatMap(assignment -> {
      repository.add(assignment);
      return Mono.just(assignment);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, SingleAssignment.class);
  }
}