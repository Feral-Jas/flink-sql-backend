package com.css.flink.backend.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class GetCommandTest {
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
    public GetCommandTest(){};
    @Autowired
    public GetCommandTest(String mode, String localSqlPluginPath, String remoteSqlPluginPath, String flinkconf, String flinkJarPath, String pluginLoadMode, String planner, String launcherJar, String startUpClass) {
        this.mode = mode;
        this.localSqlPluginPath = localSqlPluginPath;
        this.remoteSqlPluginPath = remoteSqlPluginPath;
        this.flinkconf = flinkconf;
        this.flinkJarPath = flinkJarPath;
        this.pluginLoadMode = pluginLoadMode;
        this.planner = planner;
        this.launcherJar = launcherJar;
        this.startUpClass = startUpClass;
    }

    public static void main(String[] args) {
        GetCommandTest get=new GetCommandTest();
        Map<String,String> map=new HashMap<>();
        map.put("mode",get.mode);
        map.put("name","name");
        map.put("sql","select01");
        map.put("localSqlPluginPath",get.localSqlPluginPath );
        map.put("remoteSqlPluginPath",get.remoteSqlPluginPath);
        map.put("flinkconf",get.flinkconf);
        map.put("flinkJarPath", get.flinkJarPath);
        map.put("pluginLoadMode",get.pluginLoadMode);
        map.put("planner", get.planner);
        String startCommand="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            startCommand+="-"+entry.getKey()+" "+entry.getValue()+" ";
        }
        System.out.println(startCommand);
    }
}
