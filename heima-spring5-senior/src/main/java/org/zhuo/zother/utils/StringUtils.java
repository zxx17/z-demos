package org.zhuo.zother.utils;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 * 对字符串处理的工具类
 * </p>
 */

public class StringUtils {
    /**
     * 判断两个字符串是否相等（空字符串也算相等）
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 是否相等
     */
    public static boolean equals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.equals(s2);
    }
}
