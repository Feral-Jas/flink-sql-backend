package com.css.flink.backend.job.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
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
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "uuid",unique = true)
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "sql")
    private String sql;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    @CreatedDate
    private Date createdTime;

    @Column(name = "modified_time")
    @LastModifiedDate
    private Date modifiedTime;

    /**
     * CREATE/EDIT JOB Constructor
     * @param name
     * @param sql
     * @param description
     */
    public Job(String name,String sql, String description) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.sql = sql;
        this.description = description;
    }

    public Job(String jobId) {
        this.uuid = jobId;
    }

    public boolean hasUuid(){
        return uuid==null;
    }
    @Override
    public String toString() {
        return "Job{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", sql='" + sql + '\'' +
                ", description='" + description + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
    public String toJson(){
        return "{\n" +
                "    \"name\":"+name+",\n" +
                "    \"sql\":"+sql+",\n" +
                "    \"description\":"+description+"\n" +
                "}";
    }
}
