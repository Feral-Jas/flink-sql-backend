package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author marshal
 * @date 2021/2/19
 * @description
 */
@Component
public class JobHelper {
    private Config config;
    public JobHelper(Config config){
        this.config = config;
    }
    public String getCommand(String name, String sql) {
        Map<String,String> map=new HashMap<>(9);
        String value= null;
        try {
            value = URLEncoder.encode(sql, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("mode",config.mode);
        map.put("name",name);
        map.put("sql",value);
        map.put("localSqlPluginPath",config.localSqlPluginPath);
        map.put("remoteSqlPluginPath",config.remoteSqlPluginPath);
        map.put("flinkconf",config.flinkconf);
        map.put("flinkJarPath", config.flinkJarPath);
        map.put("planner", config.planner);
        String startCommand="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            startCommand+="-"+entry.getKey()+" "+entry.getValue()+" ";
        }
        return startCommand;
    }
    public String run(String command){
        URL url = null;
        try {
            url = new URL(config.launcher);
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
            Class<?> clazz = urlClassLoader.loadClass(config.entry);
            Method method = clazz.getMethod("mainReturnJobId",String[].class);
            String invoke = (String) method.invoke(null, (Object) command.split(" "));
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
