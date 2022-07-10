### 二叉树的遍历

[颜色标记法遍历二叉树](https://leetcode.cn/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/)

兼具栈迭代方法的高效，又像递归方法一样简洁易懂，更重要的是，这种方法对于前序、中序、后序遍历，能够写出完全一致的代码。

其核心思想如下：

使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
如果遇到的节点为灰色，则将节点的值输出。


```go
// IsValidBST 是否有效二叉搜索树
func IsValidBST(root *TreeNode) bool {
	// 颜色标记法
	type ColorNode struct {
		color int
		node  *TreeNode
	}
	stack := []ColorNode{
		{0, root},
	}

	result := []int{}
	for len(stack) > 0 {
		current := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if current.node == nil {
			continue
		}
		if current.color == 0 {
			stack = append(stack, ColorNode{0, current.node.Right})
			stack = append(stack, ColorNode{1, current.node})
			stack = append(stack, ColorNode{0, current.node.Left})
		} else {
			if len(result) > 0 && current.node.Val <= result[len(result)-1] {
				return false
			}
			result = append(result, current.node.Val)
		}
	}
	return true
}
```