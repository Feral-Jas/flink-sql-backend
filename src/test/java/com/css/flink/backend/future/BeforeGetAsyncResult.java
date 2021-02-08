package com.css.flink.backend.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class BeforeGetAsyncResult {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        ExecutorService executorService= Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(() -> {
            return "new thread";
        });
        executorService.shutdown();
        System.out.println(future.get());
    }
}
