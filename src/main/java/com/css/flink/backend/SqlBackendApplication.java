package com.css.flink.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author liuchenyu
 */
@SpringBootApplication
@RestController
public class SqlBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlBackendApplication.class, args);
    }

    @GetMapping("test")
    public Mono<String> test(){
        return Mono.just("hello world");
    }

}
