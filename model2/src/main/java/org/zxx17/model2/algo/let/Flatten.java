package org.zxx17.model2.algo.let;


import org.zxx17.model2.algo.sxl.tree.TreeNode;


/**
 * 114. 二叉树展开为链表.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/8/15
 **/
public class Flatten {

    /**
     * 按照前序遍历的顺序展开成链表【中左右】
     */
    public void fun1(TreeNode root) {
        while(root != null){
            if(root.left!=null){
                TreeNode leftMostRight = root.left;
                while(leftMostRight.right != null){
                    leftMostRight = leftMostRight.right;
                }

                leftMostRight.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    /**
     * 按照中序遍历的顺序展开成链表【左中右】
     */



    /**
     * 按照后续遍历的顺序开成链表【左右中】
     */





}
