package org.zhuo.zlearning.jvm.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xinxuan Zhuo
 * @version 2024/4/12
 * <p>
 * Java堆溢出
 * 堆存放的是对象的的实例
 * </p>
 */

public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while(true){
            list.add(new OOMObject());
        }
    }



}
