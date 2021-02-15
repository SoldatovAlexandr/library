package edu.asoldatov.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("edu.asoldatov.library.model")
@EnableJpaRepositories(basePackages = "edu.asoldatov.library.repository")
public class LibraryServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServer.class);

    public static void main(final String[] args) {
        LOGGER.info("Start application");
        SpringApplication.run(LibraryServer.class);
        LOGGER.info("Stop application");
    }
}
