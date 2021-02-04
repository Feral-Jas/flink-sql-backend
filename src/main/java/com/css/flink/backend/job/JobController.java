package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description used for sql job api end point
 */
@RestController
public class JobController {

    private final JobService jobService;
    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity findJob(){
        return ResponseEntity
                .of(Optional.of(
                        jobService.findAll()
                ));
    }

    @PostMapping("/jobs")
    public ResponseEntity createJob(@RequestBody Job job){
        return ResponseEntity
                .of(Optional.of(
                        jobService.save(job)
                ));
    }

    @PutMapping("/jobs/{jobId}")
    public ResponseEntity editJob(@RequestBody Job job, @PathVariable String jobId){
        job.setUuid(jobId);
        return ResponseEntity
                .of(Optional.of(
                        jobService.save(job)
                ));
    }

    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity deleteJob(@PathVariable String jobId){
        return ResponseEntity
                .of(Optional.of(
                        jobService.delete(jobId)
                ));
    }

}
