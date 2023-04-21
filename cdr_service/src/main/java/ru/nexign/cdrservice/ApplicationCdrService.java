package ru.nexign.cdrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationCdrService {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationCdrService.class, args);

    }

}
