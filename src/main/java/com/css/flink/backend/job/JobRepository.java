package com.css.flink.backend.job;

import com.css.flink.backend.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author liuchenyu
 * @date 2021/2/4
 * @description job interface repository
 */
@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

}
