package taskapi.assignment.infrastructure;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import taskapi.assignment.domain.AssignmentRepository;
import taskapi.assignment.domain.SingleAssignment;

public class AssignmentRouterTest {

    private WebTestClient client;

    @Before
    public void setUp(){
        final AssignmentRepository assignmentRepository = new MapAssignmentRepository();
        assignmentRepository.add(new SingleAssignment("1", "1"));
        assignmentRepository.add(new SingleAssignment("2", "2"));
        final AssignmentHandler assignmentHandler = new AssignmentHandler(assignmentRepository);
        final AssignmentRouter assignmentRouter = new AssignmentRouter();
        client = WebTestClient.bindToRouterFunction(assignmentRouter.assignmentRoute(assignmentHandler)).build();
    }

    @Test
    public void getAll() {
        client.get().uri("/assignments")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody().json("[{\"personId\":\"1\",\"taskIds\":[\"1\"]},{\"personId\":\"2\",\"taskIds\":[\"2\"]}]");
    }

    @Test
    public void getAllByPersonId() {
        client.get().uri("/assignments?personId=1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody().json("[{\"personId\":\"1\",\"taskIds\":[\"1\"]}]");
    }

    @Test
    public void addOne() {
        client.post().uri("/assignments")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new SingleAssignment("1", "2"))
                .exchange()
                .expectBody().json("{\"personId\":\"1\",\"taskId\":\"2\"}");
    }
}