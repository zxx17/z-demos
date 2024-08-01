package org.zxx17.model2.dmsll.algo;

import org.zxx17.model2.dmsll.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/1
 **/
public class Traverse {
    /**
     * FEAT：前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * 前序遍历递归法
     */
    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * FEAT：层序遍历
     */
    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> levelOrder01(TreeNode root) {
        fun1(root, 0);
        return resultList;
    }
    public List<List<Integer>> levelOrder02(TreeNode root) {
        fun2(root);
        return resultList;
    }

    /**
     * 层序遍历递归法
     */
    private void fun1(TreeNode root, int deep) {
        if (root == null) return;
        deep++;

        if (resultList.size() < deep) {
            resultList.add(new ArrayList<>());
        }
        resultList.get(deep - 1).add(root.val);
        fun1(root.left, deep);
        fun1(root.right, deep);
    }

    /**
     * 层序遍历 借助队列迭代法
     */
    private void fun2(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addFirst(root);

        while (!queue.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            int len = queue.size();

            while (len > 0) {
                TreeNode tmpNode = queue.poll();
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) queue.offer(tmpNode.left);
                if (tmpNode.right != null) queue.offer(tmpNode.right);
                len--;
            }

            resultList.add(itemList);
        }


    }


}
