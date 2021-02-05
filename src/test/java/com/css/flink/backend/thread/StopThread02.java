package com.css.flink.backend.thread;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class StopThread02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
