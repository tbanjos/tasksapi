package taskapi;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.INFO);
		SpringApplication.run(App.class, args);
	}
}