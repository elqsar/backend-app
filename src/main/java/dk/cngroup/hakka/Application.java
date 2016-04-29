package dk.cngroup.hakka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}