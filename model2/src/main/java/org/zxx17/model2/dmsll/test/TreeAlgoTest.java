package org.zxx17.model2.dmsll.test;

import org.junit.jupiter.api.Test;
import org.zxx17.model2.dmsll.TreeNode;
import org.zxx17.model2.dmsll.algo.Traverse;

import java.util.List;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/1
 **/
public class TreeAlgoTest {
    @Test
    public void testLevelOrder1() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(6)));
        Traverse traverse = new Traverse();
        List<List<Integer>> result = traverse.levelOrder01(root);
        // Print the result
        System.out.println("Level Order Traversal:");
        for (List<Integer> level : result) {
            System.out.println(level);
        }
    }

    @Test
    public void testLevelOrder2() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(6)));
        Traverse traverse = new Traverse();
        List<List<Integer>> result = traverse.levelOrder02(root);
        // Print the result
        System.out.println("Level Order Traversal:");
        for (List<Integer> level : result) {
            System.out.println(level);
        }
    }



}
