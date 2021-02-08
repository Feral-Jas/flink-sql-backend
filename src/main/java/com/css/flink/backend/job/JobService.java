package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.css.flink.backend.job.utils.InvokeUtil;
import com.css.flink.backend.job.utils.StartCommandUtil;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description functions to handle sql job requests
 */
@Service
public class JobService {
    private final JobRepository jobRepository;

    Map<String,String> map=new HashMap<>(1);

    @Value(value = "${flink.entry}")
    private String flinkConf;

    @Autowired
    private StartCommandUtil startCommandUtil;
    @Autowired
    private InvokeUtil invokeUtil;
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public Map findAll() {
        HashMap<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("jobs",jobRepository.findAll());
        return resultMap;
    }

    public Map save(Job job) {
        HashMap<String, Object> resultMap = Maps.newHashMap();
        if(job.hasUuid()){
            resultMap.put("created",jobRepository.save(job));
        }else{
            resultMap.put("edited",jobRepository.save(job));
        }
        return resultMap;
    }

    public Map delete(String jobId) {
        HashMap<String, Object> resultMap = Maps.newHashMap();
        jobRepository.delete(new Job(jobId));
        resultMap.put("deleted",jobId);
        return resultMap;
    }
    public Map executeJob(String jobId){
        Optional<Job> one = jobRepository.findOne(Example.of(new Job(jobId)));
        HashMap<String, Object> resultMap = Maps.newHashMap();
        String s = runJob();
        resultMap.put("res",s);
        return resultMap;
    }

    public String runJob(Job job){
        String startCommand =startCommandUtil.getCommand(job.getName(),job.getSql());
        String jobId = invokeUtil.run(startCommand);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        map.put("jobId",jobId);
        return gson.toJson(map);

    }
}
