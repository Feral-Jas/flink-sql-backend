package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description MockTest for web layer
 */
@WebMvcTest(JobController.class)
@Setter
@Getter
class JobServiceTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    public Gson gson = new GsonBuilder().create();
    @MockBean
    private JobService jobService;

    @Autowired
    private MockMvc mockMvc;

    private HashMap<String, Object> testResult = Maps.newHashMap();

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
        Job job = new Job("jobName", "sql", "create test");
        HashMap<Object, Object> nap = Maps.newHashMap();
        nap.put("james","james");
        when(jobService.save(job)).thenReturn(nap);
        this.mockMvc
                .perform(
                        post("/jobs")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(gson.toJson(job))
                        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void editTest() throws Exception {
        Job job = new Job("jobName", "sql", "edit test");
        when(jobService.save(job)).thenReturn(null);
        this.mockMvc
                .perform(
                        put("/jobs/{id}","here_is_jobId_to_edit")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(gson.toJson(job))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        String jobId = "here_is_jobId_to_delete";
        when(jobService.delete(jobId)).thenReturn(null);
        this.mockMvc
                .perform(
                        delete("/jobs/{id}","jobId"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}