package com.css.flink.backend.job.repository;


import com.css.flink.backend.job.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author liuchenyu
 * @date 2021/2/3
 * @description dameng repository implementation
 */
@Repository
public class DmJobRepository implements CrudRepository<Job, String> {


    @Override
    public <S extends Job> S save(S s) {
        return null;
    }

    @Override
    public <S extends Job> Iterable<S> saveAll(Iterable<S> iterable) {

        return null;
    }

    @Override
    public Optional<Job> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Job> findAll() {
        return null;
    }

    @Override
    public Iterable<Job> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Job job) {

    }

    @Override
    public void deleteAll(Iterable<? extends Job> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
