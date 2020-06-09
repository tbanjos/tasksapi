package taskapi.assignment.infrastructure;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taskapi.assignment.domain.PersonAssignments;
import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.SingleAssignment;

@Component
public class AssignmentHandler {

  private final AssignmentRepository repository;

  public AssignmentHandler(AssignmentRepository repository) {
    this.repository = repository;
  }

  Mono<ServerResponse> getAll(ServerRequest request) {
    Flux<PersonAssignments> res = Flux.fromIterable(request.queryParam("personId")
            .map(personId -> {
              final Optional<PersonAssignments> allByPerson = repository.getAllByPerson(personId);
              return allByPerson.map(Collections::singletonList).orElseGet(ArrayList::new);
            })
            .orElseGet(repository::getAll));
    return ok().contentType(MediaType.APPLICATION_JSON).body(res, PersonAssignments.class);
  }

  Mono<ServerResponse> addOne(ServerRequest request) {
    Mono<SingleAssignment> mono = request.bodyToMono(SingleAssignment.class).flatMap(assignment -> {
      repository.add(assignment);
      return Mono.just(assignment);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, SingleAssignment.class);
  }
}