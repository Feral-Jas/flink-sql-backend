package com.css.flink.backend.thread;

import lombok.SneakyThrows;

import java.net.ServerSocket;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class StopThread01 extends Thread{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(2000);
        thread.stop();
    }
}
