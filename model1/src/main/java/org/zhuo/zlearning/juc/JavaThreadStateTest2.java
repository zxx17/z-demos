package org.zhuo.zlearning.juc;

import static org.zhuo.zlearning.juc.common.CommonFun.sleep;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/11
 * <p>
 * 打印6种java线程的状态（出现场景）
 * </p>
 */

public class JavaThreadStateTest2 {

    public static void main(String[] args) throws InterruptedException {
        // NEW
        Thread newState = new Thread(()->{
            System.out.println("我不会被打印");
        }, "newState");

        // Running
        Thread runningState = new Thread(()->{
            // 不释放的对象锁
            synchronized (JavaThreadStateTest2.class){
                while (true){
                    // doAction
                }
            }
        }, "runningState");
        runningState.start();

        // Terminal
        Thread terminalState = new Thread(()->{
            System.out.println("打印完就退场");
        }, "terminalState");
        terminalState.start();

        // Block
        Thread blockState = new Thread(()->{
            synchronized (JavaThreadStateTest2.class){
                System.out.println("我打印不出来");
            }
        });
        blockState.start();

        // WAITING
        Thread waitingState = new Thread(()->{
            try {
                runningState.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waitingState.start();

        // TIMED_WAiTING
        Thread timedWaitingState = new Thread(()->{
            try {
                sleep(100000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        timedWaitingState.start();


        sleep(2);

        System.out.println("newState: "+newState.getState());
        System.out.println("runningState: "+runningState.getState());
        System.out.println("terminalState: "+terminalState.getState());
        System.out.println("blockState: "+blockState.getState());
        System.out.println("waitingState: "+waitingState.getState());
        System.out.println("timedWaitingState: "+timedWaitingState.getState());

    }
}
