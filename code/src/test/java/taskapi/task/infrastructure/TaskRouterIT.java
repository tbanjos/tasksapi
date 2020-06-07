package taskapi.task.infrastructure;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import taskapi.task.domain.Task;

public class TaskRouterIT {

    WebTestClient client;

    @Before
    public void setUp() {
        final MapTaskRepository taskRepository = new MapTaskRepository();
        final TaskHandler taskHandler = new TaskHandler(taskRepository);
        final TaskRouter taskRouter = new TaskRouter();
        taskRepository.add(new Task("1", "firstTask"));
        taskRepository.add(new Task("2", "secondTask"));
        client = WebTestClient.bindToRouterFunction(taskRouter.taskRoute(taskHandler)).build();
    }

    @Test
    public void getAll() {
        client.get().uri("/tasks")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[{\"id\":\"1\",\"description\":\"firstTask\"},{\"id\":\"2\",\"description\":\"secondTask\"}]");
    }

    @Test
    public void addOne() {
        client.post().uri("/tasks")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new Task("3","newTask"))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":\"3\",\"description\":\"newTask\"}");
    }

    @Test
    public void getOne() {
        client.get().uri("/tasks/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":\"1\",\"description\":\"firstTask\"}");
    }

    @Test
    public void updateOne() {
        client.put().uri("/tasks/1")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new Task("1","changed"))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":\"1\",\"description\":\"changed\"}");
    }

    @Test
    public void deleteOne() {
        client.delete().uri("/tasks/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}