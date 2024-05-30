package org.zhuo.zlearning.juc.jg;

public class Singleton {

    private volatile static Singleton uniqueInstance = null;


    /**
     * 获取Singleton类的唯一实例。
     * 这个方法使用了双重检查锁定（Double Checked Locking）模式来确保线程安全地实例化单例对象。
     * 第一次检查是为了避免不必要的锁定，如果实例已经存在，就无需再次进入同步块。
     * 第二次检查是在同步块内部进行，以确保只有一个线程可以实例化对象。
     *
     * @return Singleton类的唯一实例。
     */
    public static Singleton getInstance() {
        // 检查实例是否已经存在，如果不存在则进入同步块
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                // 在同步块内再次检查实例是否存在，避免多线程环境下的重复实例化
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        // 返回Singleton的唯一实例
        return uniqueInstance;
    }


}
