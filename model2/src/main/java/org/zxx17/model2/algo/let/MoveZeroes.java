package org.zxx17.model2.algo.let;

import java.util.Scanner;

/**
 * 283. 移动零.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/23
 **/
public class MoveZeroes {

    // 除了0都移动，那么只要对后面的值补零就行 快慢指针法
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

    }


    // acm写法
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLen = sc.nextInt();
        int[] nums = new int[numLen];
        for (int i = 0; i < numLen; i++) {
            nums[i] = sc.nextInt();
        }

        int index = 0;
        for (int i = 0; i < numLen; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (int i = index; i < numLen; i++) {
            nums[i] = 0;
        }
        for (int i = 0; i < numLen; i++) {
            System.out.print(nums[i] + "\t");
        }
    }


}
