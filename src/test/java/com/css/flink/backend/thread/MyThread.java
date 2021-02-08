package com.css.flink.backend.thread;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class MyThread extends Thread{
    private int i = 0;
    public void run(){
        super.run();
        try {
            while (true){
                System.out.println("i=" + i);
                i++;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
