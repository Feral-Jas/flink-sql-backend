package com.css.flink.backend.job.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author marshal
 */
@Component
public class InvokeUtil {
    @Value("${StartUpPara.launcherJar}")
    public String launcherJar;
    @Value("${StartUpPara.startUpClass}")
    public String startUpClass;
    public String run(String command){
        URL url = null;
        try {
            url = new URL(launcherJar);
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
            Class<?> clazz = urlClassLoader.loadClass(startUpClass);
            Method method = clazz.getMethod("mainReturnJobId",String[].class);
            String invoke = (String) method.invoke(null, (Object) command.split(" "));
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
