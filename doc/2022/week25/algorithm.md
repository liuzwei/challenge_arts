## 1.算法题目

---------

**二叉树的后续遍历**

https://leetcode.cn/problems/binary-tree-postorder-traversal/

给你一棵二叉树的根节点 `root` ，返回其节点值的 后序遍历 。



## 2.题解说明

----------
首先要先明白二叉树的几种遍历顺序：

- 前序遍历：根节点-->左树-->右树
- 中序遍历：左树-->根节点-->右树
- 后续遍历：左树-->右树-->根节点

```java
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
```

用递归的思路是比较容易的，还可以用迭代的方法解决；
```java
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
```


## 3.总结

--------------
