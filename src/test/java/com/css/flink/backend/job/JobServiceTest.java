package com.css.flink.backend.job;

import com.css.flink.backend.job.repository.DmJobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description
 */
class JobServiceTest {
    @Autowired
    private DmJobRepository dmJobRepository;
    @Test
    void getAllJob() {
        System.out.println(dmJobRepository.findAll());
    }
}