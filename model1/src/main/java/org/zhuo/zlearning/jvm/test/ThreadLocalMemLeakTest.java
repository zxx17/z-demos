package org.zhuo.zlearning.jvm.test;

import java.util.concurrent.TimeUnit;

/**
 * @author Xinxuan Zhuo
 * @version 2024/4/13
 * <p>
 * 测试ThreadLcoal内存泄漏
 * </p>
 */

public class ThreadLocalMemLeakTest {


    public static void main(String[] args) throws InterruptedException {
//        func1();
//        func2();
        func3();
    }

    /**
     * ThreadLocal发生内存泄漏场景
     */
    public static void func1() throws InterruptedException {
        ThreadLocal<Object> local = new ThreadLocal<>();
        local.set(new ThreadLocalMemLeakTest());
        local = null;
        // 手动触发GC，此时ThreadLocal被回收，那么value是否被回收呢？
        System.gc();
        // GC是异步执行的，主线程Sleep一会，等待对象回收
        TimeUnit.SECONDS.sleep(1);
    }


    /**
     * ThreadLocal不发生内存泄漏场景1
     */
    public static void func2() throws InterruptedException {
        ThreadLocal<Object> local = new ThreadLocal<>();
        local.set(new ThreadLocalMemLeakTest());
        // 手动remove
        local.remove();
        local = null;
        // 手动触发GC，此时ThreadLocal被回收，那么value是否被回收呢？
        System.gc();
        // GC是异步执行的，主线程Sleep一会，等待对象回收
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * ThreadLocal不发生内存泄漏场景
     */
    public static void func3() throws InterruptedException {
        ThreadLocal<Object> local = new ThreadLocal<>();
        local.set(new ThreadLocalMemLeakTest());
        local = null;
        // 手动触发GC，此时ThreadLocal被回收，那么value是否被回收呢？
        System.gc();
        // GC是异步执行的，主线程Sleep一会，等待对象回收
        TimeUnit.SECONDS.sleep(1);

        // 往ThreadLocalMap多get()几次，因为get()过程会触发清理
        for (int i = 0; i < 10; i++) {
            new ThreadLocal<>().get();
        }
//        System.gc();
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * 在JDK9中被标记为废弃（因为他不可靠）
     * <a href="https://blog.csdn.net/qq_15612527/article/details/97019467">...</a>
     */
    @Override
    protected void finalize() throws Throwable {
        System.err.println("对象被回收了");
    }
}
