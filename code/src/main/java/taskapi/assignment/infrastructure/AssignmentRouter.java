package taskapi.assignment.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AssignmentRouter {

	@Bean
	public RouterFunction<ServerResponse> assignmentRoute(AssignmentHandler handler) {

    return RouterFunctions.route()
			.GET("/assignments", handler::getAll)
			.POST("/assignments", handler::addOne)
			.build();
	}
}