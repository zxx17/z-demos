package org.zxx17.model2.dmsll.algo;


import org.zxx17.model2.dmsll.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * 二叉树的层平均值  simple.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/2
 **/
public class LC637Sim {



    /**
     * 解法1，借助了递归
     */
    List<Double> resList = new ArrayList<>();
    List<List<Integer>> levelsList = new ArrayList<>();
    public List<Double> averageOfLevels01(TreeNode root) {
        dfs(root,0);
        for (List<Integer> integers : levelsList) {
            OptionalDouble average = integers.stream().mapToDouble(Integer::doubleValue).average();
            if (average.isPresent()){
                resList.add(average.getAsDouble());
            }
        }
        return resList;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null){
            return;
        }
        level++;
        if(levelsList.size()<level){
            levelsList.add(new ArrayList<>());
        }
        levelsList.get(level -1).add(root.val);
        dfs(root.left, level);
        dfs(root.right, level);
    }

    /**
     * 解法2 使用迭代
     */
    public List<Double> averageOfLevels02(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Double> resList = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();
            double sum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.removeFirst();
                sum += node.val;
                if (node.left != null){
                    queue.addLast(node.left);
                }
                if (node.right != null){
                    queue.addLast(node.right);
                }
            }
            resList.add(sum / levelSize);
        }

        return resList;
    }




}
