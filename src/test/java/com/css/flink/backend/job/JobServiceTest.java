package com.css.flink.backend.job;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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




}