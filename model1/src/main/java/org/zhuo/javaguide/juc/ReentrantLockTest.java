package org.zhuo.javaguide.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("ALL")
public class ReentrantLockTest {

    public static Lock lock = new ReentrantLock(true);
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread t= new Thread(() -> {
                increment();
            });
            t.start();
            t.join();
        }
        System.out.println(count);
    }

    public static void increment() {
        try {
            lock.lock();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
