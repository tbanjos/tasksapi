package taskapi.person;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class PersonRouterIT {

    WebTestClient client;

    @Before
    public void setUp() {
        final PersonRepository personRepository = new PersonRepository();
        personRepository.add(new Person("1", "Ana"));
        personRepository.add(new Person("2", "Joan"));
        final PersonHandler taskHandler = new PersonHandler(personRepository);
        final PersonRouter taskRouter = new PersonRouter();
        client = WebTestClient.bindToRouterFunction(taskRouter.personRoute(taskHandler)).build();
    }

    @Test
    public void getAll() {
        client.get().uri("/persons")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[{\"id\":\"1\",\"alias\":\"Ana\"},{\"id\":\"2\",\"alias\":\"Joan\"}]");
    }

    @Test
    public void addOne() {
        client.post().uri("/persons")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new Person("3","newPerson"))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":\"3\",\"alias\":\"newPerson\"}");
    }

    @Test
    public void getOne() {
        client.get().uri("/persons/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":\"1\",\"alias\":\"Ana\"}");
    }

    @Test
    public void updateOne() {
        client.put().uri("/persons/1")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new Person("1","changed"))
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":\"1\",\"alias\":\"changed\"}");
    }

    @Test
    public void deleteOne() {
        client.delete().uri("/persons/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getTasks() {
    }
}