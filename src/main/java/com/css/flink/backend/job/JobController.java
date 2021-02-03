package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description used for sql job api end point
 */
@RestController
@RequiredArgsConstructor
public class JobController {

    private JobService jobService;


    public Mono<ResponseEntity<Object>> runJob(@RequestBody Job request){
        return Mono.just(null);
    }

    public Mono<ResponseEntity<Object>> saveJob(@RequestBody Job request){

        return Mono.just(null   );
    }
    public Mono<ResponseEntity<Object>> createJob(@RequestBody Job request){
        return Mono.just(null);
    }
    public Mono<ResponseEntity<Object>> updateJob(@RequestBody Job request){
        return Mono.just(null);
    }
    @GetMapping("/jobs")
    public Mono<ResponseEntity<Object>> searchJob(@RequestBody Job request){
        return jobService.getAllJob().map(jobs ->
            ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                    .build()
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }



}
