package org.zxx17.model2.algo.let;


/**
 * 169. 多数元素  数组.
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/9/2
 **/
public class MajorityElement {

    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0){
                ans = nums[i];
            }
            if(ans == nums[i]){
                count++;
            }
            if (ans != nums[i]){
                count--;
            }
        }
        return ans;
    }


}
