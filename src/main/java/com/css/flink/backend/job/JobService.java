package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import com.css.flink.backend.job.utils.InvokeUtil;
import com.css.flink.backend.job.utils.StartCommandUtil;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import dm.jdbc.stat.support.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        resultMap.put("entry",flinkConf);
        return resultMap;
    }
    public String runJob(Job job){
        String startCommand =getCommand(job.getName(),job.getSql());
        String jobid = InvokeUtil.run(startCommand);
        Gson gson=new Gson();
        map.put("jobId",jobid);
        return gson.toJson(map);
    }
    @Value("${command.mode}")
    public String mode;
    @Value("${command.localSqlPluginPath}")
    public String localSqlPluginPath;
    @Value("${command.remoteSqlPluginPath}")
    public String remoteSqlPluginPath;
    @Value("${command.flinkconf}")
    public String flinkconf;
    @Value("${command.flinkJarPath}")
    public String flinkJarPath;
    @Value("${command.pluginLoadMode}")
    public String pluginLoadMode;
    @Value("${command.planner}")
    public String planner;
    @Value("${StartUpPara.launcherJar}")
    public String launcherJar;
    @Value("${StartUpPara.startUpClass}")
    public String startUpClass;
    public String getCommand(String name, String sql) {
        Map<String,String> map=new HashMap<>(9);
        String value= null;
        try {
            value = URLEncoder.encode(sql, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("mode",mode);
        map.put("name",name);
        map.put("sql",value);
        map.put("localSqlPluginPath", localSqlPluginPath);
        map.put("remoteSqlPluginPath",remoteSqlPluginPath);
        map.put("flinkconf",flinkconf);
        map.put("flinkJarPath", flinkJarPath);
        map.put("pluginLoadMode",pluginLoadMode);
        map.put("planner", planner);
        String startCommand="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            startCommand+="-"+entry.getKey()+" "+entry.getValue()+" ";
        }
        return startCommand;
    }
}
