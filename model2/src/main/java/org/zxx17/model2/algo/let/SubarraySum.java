package org.zxx17.model2.algo.let;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 560. 和为 K 的子数组.
 * 前缀和
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/9/10
 */
public class SubarraySum {

    public static int subarraySum(int[] nums, int k) {
        // 初始化哈希表，并将0初始化为1（用于处理从索引0开始的子数组）
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);

        int sumSoFar = 0;
        int count = 0;

        // 遍历数组中的每个元素
        for (int num : nums) {
            // 更新当前的前缀和
            sumSoFar += num;

            // 如果哈希表中存在 (sumSoFar - k)，则说明找到了和为 k 的子数组
            count += prefixSumCount.getOrDefault(sumSoFar - k, 0);

            // 更新前缀和计数
            prefixSumCount.put(sumSoFar, prefixSumCount.getOrDefault(sumSoFar, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(subarraySum(nums, k));
    }
}
