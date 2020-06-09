package taskapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

@Configuration
public class AppConfig {

    @Value("${database.hostname}")
    private String databaseHostname;

    public @Bean
    MongoClient mongoClient() {
        return new MongoClient(databaseHostname);
    }
}
