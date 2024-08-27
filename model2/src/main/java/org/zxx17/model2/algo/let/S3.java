package org.zxx17.model2.algo.let;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/3
 **/
public class S3 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        for (int num : nums) {
            // 找到那个最小的数
            if (!set.contains(num - 1)) {
                int curNum = num;
                int flag2 = 1; // 至少为1

                while (set.contains(curNum + 1)) {
                    curNum += 1;
                    flag2 += 1;
                }

                res = Math.max(res, flag2);
            }
        }
        return res;
    }


}
