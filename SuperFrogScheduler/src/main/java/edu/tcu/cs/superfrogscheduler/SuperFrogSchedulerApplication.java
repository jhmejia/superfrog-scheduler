package edu.tcu.cs.superfrogscheduler;

import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuperFrogSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperFrogSchedulerApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }

}
