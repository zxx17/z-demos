package org.zxx17.model2.algo.hot100;

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
        while(left < right){
            // 两端做高，需要取短的，否则水溢出，最后移动小的那一端，不断的寻找ans（max）期间比minH小的都跳过！
            int minH = Math.min(height[left], height[right]);
            int temp = minH * (right - left);
            ans = Math.max(ans, temp);

            while (height[left] <= minH && left< right){
                ++left;
            }
            while (height[right] <= minH && left < right){
                --right;
            }
        }
        return ans;

    }


}
