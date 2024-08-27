package org.zxx17.model2.algo.let;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II.   滑动窗口
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/27
 **/
public class ContainsNearbyDuplicate {

    // [1,2,3,1,2,3]  2  ERROR
    // ac 33 / 58
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        for (int l = 0, r = 0; r < len; r++) {
            int rVal = nums[r];
            while (nums[l] != rVal && r > l) {
                l++;
                int abs = Math.abs(l - r);
                return abs <= k;
            }
        }
        return false;
    }


    public boolean containsNearbyDuplicateByHash(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(map.get(nums[i]) - i )<= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }

        return false;

    }

}
