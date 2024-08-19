package org.zxx17.model2.algo.hot100;

import org.zxx17.model2.algo.sxl.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/15
 **/
public class RightSideView {


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result, int deep) {
        if(root == null){
            return;
        }
        if(deep == result.size()){
            result.add(root.val);
        }
        dfs(root.right, result, deep + 1);
        dfs(root.left, result, deep + 1);
    }


}
