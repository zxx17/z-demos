package org.zhuo.zlearning.juc;

import static org.zhuo.zlearning.juc.common.CommonFun.readFile;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/11
 * <p>
 *  java线程状态测试（6种）
 * </p>
 */

public class JavaThreadStateTest {

    public static void main(String[] args) {
        Thread readFile = new Thread(()->{
            readFile("E:\\aa.txt");
        }, "readFile_t1");
        readFile.start();

        // readFile_t1 Runnable
        System.out.println("ok");
    }


}
