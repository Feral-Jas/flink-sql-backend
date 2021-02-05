package com.css.flink.backend.job.utils;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author marshal
 */
public class InvokeUtil {
    public static String run(String command){
        URL url = null;
        try {
            url = new URL("file:///C:\\Users\\marshal\\Desktop\\share\\flinkSQLExecution\\lib\\sql.launcher-1.0-SNAPSHOT.jar");
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
            Class<?> clazz = urlClassLoader.loadClass("com.dtstack.flink.sql.launcher.LauncherMain");
            Method method = clazz.getMethod("main",String[].class);
            String invoke = (String) method.invoke(null, (Object) command.split(" "));
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
