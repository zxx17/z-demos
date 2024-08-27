package org.zxx17.model2.algo.let;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串.
 * 滑动窗口模板题
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/26
 **/
public class LengthOfLongestSubstring {


    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        char[] sc = s.toCharArray();
        Set<Character> occ = new HashSet<>();
        // 外层循环扩展右边界，内层循环扩展左边界
        for (int l = 0, r = 0; r < sc.length; r++) { //每一轮右端点都扩一个。
            char ch = sc[r]; // 当前考虑的元素
            while (occ.contains(ch)) { // 不符合的情况
                occ.remove(sc[l]);
                l++;
            }
            // 将当前元素加入
            occ.add(ch);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int res = 0;
        char[] charArray = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (int left = 0, right = 0; right < charArray.length; right++) {
            char rightChar = charArray[right];
            while(set.contains(rightChar)){
                set.remove(charArray[left]);
                left++;
            }

            set.add(rightChar);
            res = Math.max(res, right - left + 1);

        }


        System.out.println(res);
    }


}
