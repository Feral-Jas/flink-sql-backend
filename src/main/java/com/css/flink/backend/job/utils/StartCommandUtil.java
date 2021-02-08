package com.css.flink.backend.job.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ClassNAME: GetStartCommand
 * @Description: TODO(一句话描述该类的功能)
 * @Author: GS
 * @DATE: 2021/2/04 14:03
 */
@Component
public class StartCommandUtil {
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
