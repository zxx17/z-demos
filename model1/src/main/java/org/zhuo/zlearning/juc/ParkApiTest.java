package org.zhuo.zlearning.juc;

import java.util.Set;
import java.util.concurrent.locks.LockSupport;

import static org.zhuo.zlearning.juc.common.CommonFun.sleep;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/11
 * <p>
 * park是让当前的线程阻塞，放行需要清除打断标记
 * </p>
 */

public class ParkApiTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                System.out.println("work");
                LockSupport.park();
                System.out.println("打断状态： "+ Thread.currentThread().isInterrupted());
            }
        }, "t1");
        t1.start();

        sleep(1);
        t1.interrupt();
//        t1.interrupted();
    }

}
