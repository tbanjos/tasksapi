package taskapi.assignment.infrastructure;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import taskapi.assignment.domain.Assignment;
import taskapi.assignment.domain.AssignmentRepository;

public class AssignmentRouterIT {

    WebTestClient client;

    @Before
    public void setUp(){
        final AssignmentRepository assignmentRepository = new MapAssignmentRepository();
        assignmentRepository.add(new Assignment("1", "1"));
        assignmentRepository.add(new Assignment("2", "2"));
        final AssignmentHandler assignmentHandler = new AssignmentHandler(assignmentRepository);
        final AssignmentRouter assignmentRouter = new AssignmentRouter();
        client = WebTestClient.bindToRouterFunction(assignmentRouter.assignmentRoute(assignmentHandler)).build();
    }

    @Test
    public void getAll() {
        client.get().uri("/assignments")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody().json("[{\"personId\":\"1\",\"taskId\":\"1\"},{\"personId\":\"2\",\"taskId\":\"2\"}]");
    }

    @Test
    public void getAllByPersonId() {
        client.get().uri("/assignments?personId=1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody().json("[{\"personId\":\"1\",\"taskId\":\"1\"}]");
    }

    @Test
    public void addOne() {
        client.post().uri("/assignments")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new Assignment("1", "2"))
                .exchange()
                .expectBody().json("{\"personId\":\"1\",\"taskId\":\"2\"}");
    }
}