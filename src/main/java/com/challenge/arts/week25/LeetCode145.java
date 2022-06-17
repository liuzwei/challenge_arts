package com.challenge.arts.week25;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的遍历
 * 二叉树的遍历分三种遍历方式，前序、中序、后序，前中后的顺序是基于根节点所在位置区分的。
 * 前序遍历： 根节点-->左树-->右树
 * 中序遍历： 左树-->根节点-->右树
 * 后序遍历： 左树-->右树-->根节点
 * @author liuzhaowei
 * @date 2022/6/15 13:16
 */
public class LeetCode145 {

    /**
     * 迭代遍历
     * @param root
     * @return
     */
    public List<Integer> iterateTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //需要一个栈结构临时存放Node
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !deque.isEmpty()){
            // 先遍历左子树，直到左子树为空
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            // 左子树遍历完后，开始出栈
            root = deque.pop();
            //如果出栈的节点没有右子树，则不再需要遍历
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            }else {
                // 如果出栈的节点，还有右子树，则再次进栈
                deque.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList();
        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        if(root.left == null && root.right == null){
            list.add(root.val);
            return list;
        }
        list.add(root.val);
        return list;
    }

}
