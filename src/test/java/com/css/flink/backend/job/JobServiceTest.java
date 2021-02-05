package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description MockTest for web layer
 */
@WebMvcTest(JobController.class)
@Getter
@Setter
class JobServiceTest {

    @MockBean
    private JobService jobService;

    @Autowired
    private MockMvc mockMvc;

    private HashMap<Object, Object> testResult = Maps.newHashMap();

    @Test
    public void findAllTest() throws Exception {
        testResult.put("result","findAll test complete");
        when(jobService.findAll()).thenReturn(testResult);
        this.mockMvc.perform(get("/jobs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'result':'findAll test complete'}"));
    }

    @Test
    public void createTest() throws Exception {
        testResult.put("result","create test complete");
        Job job = new Job("jobName", "sql", "create test");
        when(jobService.save(job)).thenReturn(testResult);
        this.mockMvc
                .perform(
                        post("/jobs")
                                .contentType("application/json")
                                .content(job.toJson())
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'result':'create test complete'}"));
    }

    @Test
    public void editTest() throws Exception {
        testResult.put("result","edit test complete");
        Job job = new Job("jobName", "sql", "edit test");
        when(jobService.save(job)).thenReturn(testResult);
        this.mockMvc.perform(put("/jobs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'result':'edit test complete'}"));
    }

}