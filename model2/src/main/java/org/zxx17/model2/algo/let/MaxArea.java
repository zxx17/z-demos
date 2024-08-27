package org.zxx17.model2.algo.let;

import java.util.Scanner;

/**
 * 11. 盛最多水的容器.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/23
 **/
public class MaxArea {

    /**
     * 二分法，左右指针
     */
    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            // 两端做高，需要取短的，否则水溢出，最后移动小的那一端，不断的寻找ans（max）期间比minH小的都跳过！
            int minH = Math.min(height[left], height[right]);
            int temp = minH * (right - left);
            ans = Math.max(ans, temp);

            while (height[left] <= minH && left < right) {
                ++left;
            }
            while (height[right] <= minH && left < right) {
                --right;
            }
        }
        return ans;

    }


    /**
     * acm写法
     */
    public static void main(String[] args) {
        int[] height = input02();

        int ans = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right){
            int minH = Math.min(height[left], height[right]);
            int temp = minH * (right - left);
            ans = Math.max(ans, temp);

            while(height[left] <= minH && left<right){
                left++;
            }
            while(height[right] <= minH && left<right){
                right--;
            }
        }
        System.out.println(ans);
    }

    private static int[] input01(){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] height = new int[m];
        for (int i = 0; i < m; i++) {
            height[i] = sc.nextInt();
        }
        return height;
    }

    private static int[] input02(){
        Scanner sc = new Scanner(System.in);
        String[] val = sc.next().split(",");
        int[] height = new int[val.length];
        for (int i = 0; i < val.length; i++) {
            height[i] = Integer.parseInt(val[i]);
        }
        return height;
    }


}
