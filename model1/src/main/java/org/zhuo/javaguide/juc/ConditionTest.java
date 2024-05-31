package org.zhuo.javaguide.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean dataReady = false;

    public void produceData() {
        lock.lock();
        try {
            // 生产数据
            System.out.println("Produced data...");
            dataReady = true;
            // 唤醒等待该条件的线程
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consumeData() throws InterruptedException {
        lock.lock();
        try {
            // 如果数据尚未准备好，则等待
            while (!dataReady) {
                System.out.println("Waiting for data...");
                condition.await();
            }
            // 消费数据
            System.out.println("Consuming data...");
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        // 线程1：生产数据
        Thread producerThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // 模拟生产数据的耗时操作
                conditionTest.produceData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 线程2：消费数据
        Thread consumerThread = new Thread(() -> {
            try {
                conditionTest.consumeData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }


}
