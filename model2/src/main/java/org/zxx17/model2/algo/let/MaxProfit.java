package org.zxx17.model2.algo.let;

/**
 * 122. 买卖股票的最佳时机 II && I.
 * 数组、贪心、动态规划！！！！！！！！
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/9/2
 **/
public class MaxProfit {

    /**
     * 计算所有涨幅累加就可以了
     */
    public int maxProfitII(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    /**
     * 计算最大涨幅
     */
    public int maxProfitI(int[] prices) {
        int max = 0;
        for (int l = 0, r = 0; r < prices.length; r++) {
            if (prices[r] < prices[l]) {
                l = r;
            } else if ((prices[r] - prices[l]) > max) {
                max = prices[r] - prices[l];
            }
        }
        return max;
    }


}
