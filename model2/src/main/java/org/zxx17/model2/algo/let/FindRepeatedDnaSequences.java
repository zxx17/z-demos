package org.zxx17.model2.algo.let;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 187. 重复的DNA序列.   滑动窗口
 * <p>
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * <p>
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 * <p>
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/27
 **/
public class FindRepeatedDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() < 10) {
            return ans;
        }
        Map<String, Integer> container = new HashMap<>();
        // 注意边界
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);
            container.put(substring, container.getOrDefault(substring, 0) + 1);
            if (container.get(substring) == 2) {
                ans.add(substring);
            }
        }
        return ans;
    }


}
