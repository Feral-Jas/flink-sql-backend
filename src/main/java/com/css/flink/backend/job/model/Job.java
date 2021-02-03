package com.css.flink.backend.job.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description sql job model
 */

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;
    private String name;
    private String sql;

    private String description;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    public Job(String name,String sql, String description) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.sql = sql;
        this.description = description;
        this.modifiedTime = LocalDateTime.now();
    }
}
