package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.google.common.collect.Maps;
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

    @Value(value = "${flink.entry}")
    private String flinkConf;

    @Autowired
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

    public String runJob(){
        Object invokeResult =null;
        try {
            URLClassLoader child = new URLClassLoader(
                    new URL[] {new URL("file:///Users/liuchenyu/IdeaProjects/flinkStreamSQL/lib/sql.launcher-1.0-SNAPSHOT.jar")});
            Class<?> clazz = Class.forName("com.dtstack.flink.sql.launcher.LauncherMain", true, child);
            Method mainEntry = clazz.getMethod("main", String[].class);
            invokeResult = mainEntry.invoke(null, (Object) new String[]{"/Users/liuchenyu/Desktop/work/FlinkSqlExecutor/jobs/kafka2kafka/kafka2kafka.json"});

        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return invokeResult.toString();
    }
}
