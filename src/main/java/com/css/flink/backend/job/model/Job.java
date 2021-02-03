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
@Table(name = "JOB_INFO")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid",unique = true)
    private String uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "sql")
    private String sql;
    @Column(name = "description")
    private String description;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    public Job(String name,String sql, String description) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.sql = sql;
        this.description = description;
        this.createdTime = LocalDateTime.now();
    }
}
