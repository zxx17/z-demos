package org.zxx17.model2.algo.let;

import java.util.*;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/21
 **/
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return new ArrayList<>(result);
            }
            int left = i + 1;
            int right = nums.length - 1;
            while(right > left){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0){
                    right--;
                } else if (sum < 0) {
                    left++;
                }else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }


        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLen = sc.nextInt();
        int[] nums = new int[numLen];
        for (int i = 0; i < numLen; i++){
            nums[i] = sc.nextInt();
        }
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }

}
