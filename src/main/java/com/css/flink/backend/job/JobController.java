package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.css.flink.backend.job.repository.DmJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description used for sql job api end point
 */
@RestController
public class JobController {

    private JobService jobService;
    @Autowired
    private DmJobRepository dmJobRepository;

    @GetMapping("/jobs")
    public @ResponseBody Iterable<Job> searchJob(){
        return dmJobRepository.findAll();
    }



}
