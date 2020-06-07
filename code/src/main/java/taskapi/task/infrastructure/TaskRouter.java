package taskapi.task.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TaskRouter {

	@Bean
	public RouterFunction<ServerResponse> taskRoute(TaskHandler handler) {

    return RouterFunctions.route()
			.GET("/tasks", handler::getAll)
			.POST("/tasks", handler::addOne)
			.GET("/tasks/{id}", handler::getOne)
			.PUT("/tasks/{id}", handler::updateOne)
			.DELETE("/tasks/{id}", handler::deleteOne)
			.build();
	}
}