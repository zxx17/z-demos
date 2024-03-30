package org.zhuo.zlearning.juc.test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/13
 * <p>
 *
 * </p>
 */

public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<String> atomicRef = new AtomicReference<>("initial value");

        Thread thread1 = new Thread(() -> {
            atomicRef.set("new value 1");
        });

        Thread thread2 = new Thread(() -> {
            atomicRef.set("new value 2");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Value: " + atomicRef.get());
    }
}
