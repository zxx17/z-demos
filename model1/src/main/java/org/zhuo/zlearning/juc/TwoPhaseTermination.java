package org.zhuo.zlearning.juc;


import lombok.extern.slf4j.Slf4j;

import static org.zhuo.zlearning.juc.common.CommonFun.sleep;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/11
 * <p>
 * 两阶段终止模式
 * </p>
 */
public class TwoPhaseTermination {

    private Thread monitor;


    /**
     * 比如说这个方法的作用是对某个东西需要持续的某项监控资源情况
     * 用到了while(true)循环，但是要加sleep防止单核cpu100%占用率
     * 要优雅的停止这个线程的任务（让他能够料理后事）不能使用stop()
     */
    public void start(){
        monitor = new Thread(()->{
            while (true){
                Thread now = Thread.currentThread();
                if (now.isInterrupted()){
                    System.out.println("关闭该关闭的资源");
                    break;
                }
                System.out.println("work...work...");
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // sleep，wait会清除打断标记
                    now.interrupt();
                }

            }
        });
        monitor.start();
    }

    public void generalStop(){
        monitor.interrupt();
    }


    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination termination = new TwoPhaseTermination();
        termination.start();
        sleep(2);
        termination.generalStop();
    }

}
