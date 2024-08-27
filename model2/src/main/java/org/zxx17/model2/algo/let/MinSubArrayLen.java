package org.zxx17.model2.algo.let;

import java.util.Scanner;

/**
 * 209. 长度最小的子数组.
 * 滑动窗口
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/26
 **/
public class MinSubArrayLen {


    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE; // 初始化为最大可能值
        int sum = 0;
        int len = nums.length;

        for (int l = 0, r = 0; r < len; r++) {
            sum += nums[r];
            while (sum >= target) {
                res = Math.min(res, r - l + 1); // 寻找最短子数组长度
                sum -= nums[l++]; // 缩小子数组范围
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res; // 如果没有找到合适的子数组，返回0
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int target = sc.nextInt();
        int nLen = sc.nextInt();
        int[] nums = new int[nLen];
        for (int i = 0; i < nLen; i++) {
            nums[i] = sc.nextInt();
        }

        int res = Integer.MAX_VALUE;
        int tempSum = 0;

        for (int l = 0, right = 0; right < nums.length; right++) {
            tempSum += nums[right];
            while (tempSum >= target) {
                res = Math.min(res, right - l + 1);
                tempSum -= nums[l++];
            }
        }
        res = res == Integer.MAX_VALUE ? 0 : res;
        System.out.println(res);


    }


}
