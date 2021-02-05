package com.css.flink.backend.thread;

/**
 * @author marshal
 * @date 2021/2/4
 * @description
 */
public class MyThread01 extends Thread{
    public void run(){
        while (true){
            if(this.isInterrupted()){
                System.out.println("线程被停止了！");
                return;
            }
            System.out.println("Time: " + System.currentTimeMillis());
        }
    }
}
