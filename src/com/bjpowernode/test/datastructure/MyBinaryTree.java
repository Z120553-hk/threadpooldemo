package com.bjpowernode.test.datastructure;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;


/**
 * 二叉树的三种遍历方式
 * 前序，根、左、右
 * 中序遍历：左、根、右
 * 后序遍历：左、右、根
 */
public class MyBinaryTree {

    private static List<Integer> result = new ArrayList<Integer>();

    public static void main(String[] args) {
        MyBinaryTree myBinaryTree = new MyBinaryTree();
        /**
         * 模拟创造一个二叉树
         *           6
         *       4      7
         *     3   5       9
         *   2
         *
         */

        MyTreeNode myTreeNode = new MyTreeNode(6);

        myTreeNode.left = new MyTreeNode(4);

        myTreeNode.right = new MyTreeNode(7);

        myTreeNode.left.left = new MyTreeNode(3);

        myTreeNode.left.left.left = new MyTreeNode(2);

        myTreeNode.right.right = new MyTreeNode(9);

        myTreeNode.left.right = new MyTreeNode(5);

        myBinaryTree.preTraverse(myTreeNode);
        System.out.print("前序遍历==>");
        for (int k : result) {
            System.out.print(k);
        }
        result.clear();
        System.out.println();

        myBinaryTree.inorderTraverse(myTreeNode);
        System.out.print("中序遍历==>");
        for (int k : result) {
            System.out.print(k);
        }
        result.clear();
        System.out.println();

        myBinaryTree.followTraverse(myTreeNode);
        System.out.print("后序遍历==>");
        for (int k : result) {
            System.out.print(k);
        }
        result.clear();


    }


    /**
     * 前序遍历
     *
     * @param root
     */
    public void preTraverse(MyTreeNode root) {
        if (root != null) {
            result.add(root.value);
            if (root.left != null) {
                preTraverse(root.left);
            }

            if (root.right != null) {
                preTraverse(root.right);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inorderTraverse(MyTreeNode root) {
        if (root != null) {
            if (root.left != null) {
                inorderTraverse(root.left);
            }
            result.add(root.value);
            if (root.right != null) {
                inorderTraverse(root.right);
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void followTraverse(MyTreeNode root) {
        if (root != null) {
            if (root.left != null) {
                followTraverse(root.left);
            }
            if (root.right != null) {
                followTraverse(root.right);
            }
            result.add(root.value);
        }
    }


    static class MyTreeNode {
        int value;
        MyTreeNode left;
        MyTreeNode right;

        public MyTreeNode(int value) {
            this.value = value;
        }
    }
}


