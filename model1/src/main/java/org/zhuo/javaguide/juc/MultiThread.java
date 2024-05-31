package org.zhuo.javaguide.juc;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {


    /**
     * 主函数：打印所有线程的ID和名称。
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        // 获取线程管理器
        ThreadMXBean threadMXBean  = java.lang.management.ManagementFactory.getThreadMXBean();
        // 获取包括守护线程和非守护线程在内的所有线程的信息，详细程度较高
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        // 遍历并打印所有线程的ID和名称
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

    }




}
