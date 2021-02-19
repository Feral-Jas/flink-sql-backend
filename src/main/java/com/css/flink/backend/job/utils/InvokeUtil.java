package com.css.flink.backend.job.utils;

import com.css.flink.backend.job.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Value("${startupparam.launcher}")
    public String launcher;
    @Value("${startupparam.entry}")
    public String entry;
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
