package org.zhuo.zlearning.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xinxuan Zhuo
 * @version 2024/3/18
 * <p>
 *
 * </p>
 */

public class TestSet {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,8);
        Set<Integer> set = new HashSet<>();

        set.addAll(list);
        System.out.println(set);

    }

}
