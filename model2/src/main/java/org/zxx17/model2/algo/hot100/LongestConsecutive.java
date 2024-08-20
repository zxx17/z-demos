package org.zxx17.model2.algo.hot100;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/20
 **/
public class LongestConsecutive {


    public int longestConsecutive(int[] nums) {
        int res = 0;
        Set<Integer> container = new HashSet<>();
        for(int num: nums){
            container.add(num);
        }

        for(int num: nums){
           if(!container.contains(num - 1)){
               int curNum = num;
               int flag = 1;

               while(container.contains(curNum+1)){
                   flag++;
                   curNum++;
               }

               res = Math.max(res, flag);
           }
        }

        return res;
    }




}
