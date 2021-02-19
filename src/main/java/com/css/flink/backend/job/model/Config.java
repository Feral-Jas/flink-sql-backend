package com.css.flink.backend.job.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author marshal
 * @date 2021/2/19
 * @description
 */
@Component
public class Config {
    @Value("${submit.mode}")
    public String mode;
    @Value("${submit.localSqlPluginPath}")
    public String localSqlPluginPath;
    @Value("${submit.remoteSqlPluginPath}")
    public String remoteSqlPluginPath;
    @Value("${submit.flinkconf}")
    public String flinkconf;
    @Value("${submit.flinkJarPath}")
    public String flinkJarPath;
    @Value("${startupparam.launcher}")
    public String launcher;
    @Value("${startupparam.entry}")
    public String entry;
}
