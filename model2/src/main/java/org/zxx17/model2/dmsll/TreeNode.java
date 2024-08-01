package org.zxx17.model2.dmsll;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/7/31
 **/
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


}
