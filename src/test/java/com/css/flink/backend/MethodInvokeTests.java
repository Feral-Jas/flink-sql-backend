package com.css.flink.backend;

import com.css.flink.backend.job.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Chenyu Liu
 * @date 2021/1/29
 * @description method to be invoke
 */
@SpringBootTest
public class MethodInvokeTests {
    @Autowired
    private JobService jobService;

    @Test
    public void Test01() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        URLClassLoader child = new URLClassLoader (
                new URL[] {new URL("file:///Users/liuchenyu/IdeaProjects/flinkStreamSQL/lib/sql.launcher-1.0-SNAPSHOT.jar")});
        Class<?> clazz = Class.forName("com.dtstack.flink.sql.launcher.LauncherMain", true, child);
        Method mainEntry = clazz.getMethod("main", String[].class);
        mainEntry.invoke(null, (Object) new String[]{"/Users/liuchenyu/Desktop/work/FlinkSqlExecutor/jobs/kafka2kafka/kafka2kafka.json"});
    }

    @Test
    public void runJobTest(){
        jobService.runJob();
    }
}
