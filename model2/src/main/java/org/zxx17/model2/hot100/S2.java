package org.zxx17.model2.hot100;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 2字母异位词分组.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/3
 **/
public class S2 {
    public List<List<String>> groupAnagrams(String[] strs) {
       return sortSolution(strs);
    }

    /**
     * 排序法，key 为排序后的字符串，value 为该字符串对应的所有字符串
     * 符合题意的str都是相同字母异序词，排序后的key是一样的
     */
    private List<List<String>> sortSolution(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            // 添加符合条件的词
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * hash计数法
     */
    private List<List<String>> hashSolution(String[] strs) {
        return null;
    }

    @Test
    public void test() {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        System.out.println(result);
    }


}
