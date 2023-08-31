package com.github.candy.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CandyStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandyStoreApplication.class, args);
        log.info("CandyStoreApplication started");
    }
}
