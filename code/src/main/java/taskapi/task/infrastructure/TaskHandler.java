package taskapi.task.infrastructure;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import taskapi.task.domain.Task;
import taskapi.task.domain.TaskRepository;

@Component
public class TaskHandler {

  private TaskRepository repository;

  public TaskHandler(TaskRepository repository) {
    this.repository = repository;
  }

  Mono<ServerResponse> getAll(ServerRequest request) {
    Flux<Task> res = Flux.fromIterable(repository.getAll());
    return ok().contentType(MediaType.APPLICATION_JSON).body(res, Task.class);
  }

  Mono<ServerResponse> addOne(ServerRequest request) {
    Mono<Task> mono = request.bodyToMono(Task.class).flatMap(task -> {
      repository.add(task);
      return Mono.just(task);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Task.class);
  }

  Mono<ServerResponse> getOne(ServerRequest request) {
    final String id = request.pathVariable("id");
    System.out.println(id);
    Mono<Task> mono = Mono.justOrEmpty(repository.get(id));
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Task.class);
  }

  Mono<ServerResponse> updateOne(ServerRequest request) {
    Mono<Task> mono = request.bodyToMono(Task.class).flatMap(newTask -> {
      final String id = request.pathVariable("id");
      Task task = repository.get(id).orElse(new Task(id, newTask.getDescription()));
      task.setDescription(newTask.getDescription());
      repository.update(task);
      return Mono.just(task);
    });
    return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Task.class);
  }

  Mono<ServerResponse> deleteOne(ServerRequest request) {
    final String id = request.pathVariable("id");
    repository.delete(id);
    return ok().build();
  }
}