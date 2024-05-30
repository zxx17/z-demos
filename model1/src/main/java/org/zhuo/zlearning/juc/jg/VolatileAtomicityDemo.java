package org.zhuo.zlearning.juc.jg;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Volatile不能保证原子性的例子
 * volatile 关键字能保证变量的可见性，但不能保证对变量的操作是原子性的。
 */
public class VolatileAtomicityDemo {
    public volatile static int count = 0;

    // Non-atomic operation on volatile field 'count'   编译器也在提醒你
    public void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        VolatileAtomicityDemo volatileAtomicityDemo = new VolatileAtomicityDemo();
        for (int i = 0; i < 1000; i++) {
            executor.execute(volatileAtomicityDemo::increment);
        }
        // 等待1.5秒，保证上面程序执行完成
        Thread.sleep(1500);
        System.out.println(count);
        executor.shutdown();
    }

}
