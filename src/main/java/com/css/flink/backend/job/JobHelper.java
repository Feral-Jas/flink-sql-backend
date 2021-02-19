package com.css.flink.backend.job;

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
    @Value("${startupparam.launcher}")
    public String launcher;
    @Value("${startupparam.entry}")
    public String entry;
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
        map.put("localSqlPluginPath",localSqlPluginPath);
        map.put("remoteSqlPluginPath",remoteSqlPluginPath);
        map.put("flinkconf",flinkconf);
        map.put("flinkJarPath", flinkJarPath);
        map.put("pluginLoadMode",pluginLoadMode);
        map.put("planner",planner);
        String startCommand="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            startCommand+="-"+entry.getKey()+" "+entry.getValue()+" ";
        }
        return startCommand;
    }
    public String run(String command){
        URL url = null;
        try {
            url = new URL(launcher);
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
            Class<?> clazz = urlClassLoader.loadClass(entry);
            Method method = clazz.getMethod("mainReturnJobId",String[].class);
            String invoke = (String) method.invoke(null, (Object) command.split(" "));
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
