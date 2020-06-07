package taskapi.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PersonRouter {

	@Bean
	public RouterFunction<ServerResponse> personRoute(PersonHandler handler) {

    return RouterFunctions.route()
			.GET("/persons", handler::getAll)
			.POST("/persons", handler::addOne)
			.GET("/persons/{id}", handler::getOne)
			.PUT("/persons/{id}", handler::updateOne)
			.DELETE("/persons/{id}", handler::deleteOne)
			.build();
	}
}