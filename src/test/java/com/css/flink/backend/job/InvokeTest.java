package com.css.flink.backend.job;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class InvokeTest {
        public static void main(String[] args) throws Exception{
            URL url = new URL("file:///C:\\Users\\marshal\\Desktop\\share\\flinkSQLExecution\\lib\\sql.launcher-1.0-SNAPSHOT.jar");
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
            Class<?> clazz = urlClassLoader.loadClass("com.dtstack.flink.sql.launcher.LauncherMain");
            Method method = clazz.getMethod("main",String[].class);
            String arg = "-mode standalone -localSqlPluginPath C:/users/marshal/Desktop/s/FlinkSQLExecutor/sqlplugins -flinkJarPath C:/users/marshal/Desktop/s/FlinkSQLExecutor/lib -name kafka2kafka -remoteSqlPluginPath /Users/liuchenyu/Desktop/work/FlinkSqlExecutor/sqlplugins -flinkconf C:/Users/marshal/Desktop/conf -planner blink -sql CREATE+TABLE+MyTable%28name+varchar%2Cchannel+varchar%29WITH%28type+%3D%27kafka%27%2CbootstrapServers+%3D%2710.15.0.26%3A9092%27%2CzookeeperQuorum+%3D%2710.15.0.26%3A2181%27%2CoffsetReset+%3D%27latest%27%2Ctopic+%3D%27kafka_producer%27%2Cparallelism+%3D%271%27%2Csourcedatatype+%3D%27json%27%29%3BCREATE+TABLE+MyResult%28name+varchar%2Cchannel+varchar%29WITH%28type+%3D%27kafka%27%2CbootstrapServers+%3D%2710.15.0.26%3A9092%27%2CzookeeperQuorum+%3D%2710.15.0.26%3A2181%27%2CoffsetReset+%3D%27latest%27%2Ctopic+%3D%27test%27%2Cparallelism+%3D%271%27%2Csourcedatatype+%3D%27json%27%29%3Binsert+into+MyResult+select+*+from+MyTable -pluginLoadMode classpath ";
            String test[]=new String[]{
                    "-mode","standalone",
                    "-name","kafkaTest",
                    "-sql","CREATE+TABLE+MyTable%28name+varchar%2Cchannel+varchar%29WITH%28type+%3D%27kafka%27%2CbootstrapServers+%3D%27127.0.0.1%3A9092%27%2CzookeeperQuorum+%3D%27127.0.0.1%3A2181%27%2CoffsetReset+%3D%27latest%27%2Ctopic+%3D%27kafka_producer%27%2Cparallelism+%3D%271%27%2Csourcedatatype+%3D%27json%27%29%3BCREATE+TABLE+MyResult%28name+varchar%2Cchannel+varchar%29WITH%28type+%3D%27kafka%27%2CbootstrapServers+%3D%27127.0.0.1%3A9092%27%2CzookeeperQuorum+%3D%27127.0.0.1%3A2181%27%2CoffsetReset+%3D%27latest%27%2Ctopic+%3D%27test%27%2Cparallelism+%3D%271%27%2Csourcedatatype+%3D%27json%27%29%3Binsert+into+MyResult+select+*+from+MyTable",
                    "-localSqlPluginPath","C:/Users/marshal/Desktop/share/flinkSQLExecution/sqlplugins",
                    "-remoteSqlPluginPath","/mnt/c/Users/marshal/Desktop/share/flinkSQLExecution/sqlplugins",
                    "-flinkconf", "C:/Users/marshal/AppData/Local/Packages/CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc/LocalState/rootfs/home/marshal/flink/flink-1.11.2/conf",
                    "-flinkJarPath","/mnt/c/Users/marshal/Desktop/share/flinkSQLExecution/lib",
                    "-pluginLoadMode","classpath",
                    "-planner","blink"
            };
            Object obj = method.invoke(null,(Object)test);
            System.out.println(obj);
        }
}
