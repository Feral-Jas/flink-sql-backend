package com.css.flink.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuchenyu
 */
@SpringBootApplication
@RestController
public class SqlBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlBackendApplication.class, args);
    }


}
