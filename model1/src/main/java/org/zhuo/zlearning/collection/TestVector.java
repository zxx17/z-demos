package org.zhuo.zlearning.collection;

import java.util.Vector;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/18
 * <p>
 *
 * </p>
 */

public class TestVector {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        for (int i = 0; i < 5; i++) {
            vector.add(i);
        }
        for (Integer integer : vector) {
            vector.remove(integer);
        }
        System.out.println(vector);

    }
}
