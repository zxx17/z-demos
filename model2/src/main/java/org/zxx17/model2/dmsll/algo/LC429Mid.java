package org.zxx17.model2.dmsll.algo;

import org.zxx17.model2.dmsll.NTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * N 叉树的层序遍历.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/2
 **/
public class LC429Mid {

    /**
     * 迭代法
     */
    public List<List<Integer>> levelOrder01(NTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> resList = new ArrayList<>();
        LinkedList<NTreeNode> queue = new LinkedList<>();

        queue.addLast(root);
        while (!queue.isEmpty()) {
            // 当前层大小
            int leavelSize = queue.size();
            //当前层遍历结果
            List<Integer> leavelList = new ArrayList<>();
            for (int i = 0; i < leavelSize; i++) {
                NTreeNode node = queue.removeFirst();
                leavelList.add(node.val);

                // 获取当前节点的子节点
                List<NTreeNode> children = node.children;
                if (children == null) {
                    continue;
                }
                // 将子节点加入队列
                for (NTreeNode child : node.children) {
                    if (child != null) {
                        queue.addLast(child);
                    }
                }
            }
            resList.add(leavelList);
        }
        return resList;

    }


}
