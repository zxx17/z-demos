package org.zxx17.model2.algo.letdaily;

import java.util.Scanner;

/**
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 * <p>
 * 周洋哥有一个整数数组 nums 和一个二维整数矩阵 queries，对于 queries[i] = [fromi, toi]，请你帮助周洋哥检查子数组 nums[fromi..toi] 是不是一个 特殊数组 。
 * <p>
 * 返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/14
 **/
public class IsArraySpecial3152 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++){
            nums[i] = scanner.nextInt();
        }
        int[][] queries = new int[m][2];
        for(int i=0; i<m; i++){
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
        }
        boolean[] res = isArraySpecial(nums, queries);
        for(boolean b : res){
            System.out.print(b + "\t");
        }
    }


    /**
     * 只需要检测以 j 为结尾的最长特殊子数组是否可以覆盖区间 [i,j]
     */
    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int len = nums.length;
        int[] dp = new int[len];
        // 计算dp[i] 也就是最长符合条件的子数组长度
        for(int i=1; i<len; i++){
            if(((nums[i] ^ nums[i-1]) & 1) != 0){
                dp[i] = dp[i-1] + 1;
            }
        }

        boolean[] res = new boolean[queries.length];
        for(int i = 0; i< queries.length; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            res[i] = dp[right] >= right-left;
        }

        return res;

    }




}
