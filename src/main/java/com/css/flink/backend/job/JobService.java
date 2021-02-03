package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.css.flink.backend.job.repository.DmJobRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description functions to handle sql job requests
 */
@Service
public class JobService {

    private DmJobRepository dmJobRepository;

    public Mono<Job> saveJob(Job job){

        return Mono.empty();
    }
    public Mono<Job> createJob(Job job){

        return Mono.just(null);
    }
    public Mono<Job> updateJob(){
        return Mono.just(null);
    }
    public Mono<Iterable<Job>> getAllJob(){
        return Mono.just(dmJobRepository.findAll());

    }

}
