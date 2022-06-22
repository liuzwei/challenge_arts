## 1.[第三大的数](https://leetcode.cn/problems/third-maximum-number/)

**题目：**
给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。

**题解说明：**

思路1：
1. 先对数组进行排序
2. 然后从大到小进行两两比较，如果找到两次不一样的，说明就找到了第三大的数
```java
public int thirdMax(int[] nums) {
        // 对数组排序
        Arrays.sort(nums);
        // 两两遍历，找到两个不一样的说明就存在第三大的
        int count = 0;
        for(int i=nums.length-1; i>0; i--){
            if(nums[i] != nums[i-1]){
                count++;
            }
            if(count == 2){
                return nums[i-1];
            }
        }
        return nums[nums.length - 1];
    }
```

思路2：
1. 类比找最大数，一般找最大数就是声明一个变量，然后循环遍历数组，每拿到一个元素就和变量做比较，如果元素值比变量值大则赋值给变量，进行下一个元素比对，直到结束。
2. 这样就可以声明三个变量，最大、次大、第三大，然后循环遍历每个元素，拿到元素后分别和最大、次大、第三大的变量比较，然后赋值。
3. 这里有一个点需要说明，声明三个变量时的初始值，题目说明了取值范围，那变量声明的值即可为（最小值-1）
4. 遍历完后，如果第三大值，还是声明的最小值，说明数组中不存在第三大值，则直接返回最大值即可。

```golang
func ThirdMax(nums []int) int {
	const MIN_VALUE = -1<<31 - 1
	var max1, max2, max3 = MIN_VALUE, MIN_VALUE, MIN_VALUE
	for _, item := range nums {
		if item > max1 {
			max1, max2, max3 = item, max1, max2
		} else if item > max2 && item < max1 {
			max2, max3 = item, max2
		} else if item > max3 && item < max2 {
			max3 = item
		}
	}
	if max3 == MIN_VALUE {
		return max1
	}
	return max3
}

```

## 2.[二叉搜索树中第K小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/)
**题目：**
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

**题解说明：**

思路1：
二叉搜索树，特点就是左节点值小于根节点值，右节点值大于根节点值，基于这个特点，采用中序遍历（左->根->右）得到一个排好序的集合，取第K个最小的元素即可。

中序遍历，首先想到的就是**递归法**
```java
 public int kthSmallest(TreeNode root, int k) {
        // 采用中序遍历，取第K个元素
        List<Integer> list = traverse(root);
        return list.get(k-1);
    }

    private List<Integer> traverse(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root.left == null && root.right == null) {
            list.add(root.val);
            return list;
        }
        if(root.left != null) {
            list.addAll(traverse(root.left));
        }

        list.add(root.val);

        if(root.right != null){
            list.addAll(traverse(root.right));
        }

        return list;
    }
```

同样也可以用**迭代法**实现：

Go实现迭代法：
```golang
func kthSmallest(root *TreeNode, k int) int {
    // 声明一个栈结构存放元素
    var stack []*TreeNode = []*TreeNode{}

    for {
        for root != nil {
            stack = append(stack, root)
            root = root.Left
        }
        stack, root = stack[:len(stack)-1], stack[len(stack)-1]
        k--
        if k==0 {
            return root.Val
        }
        root = root.Right
    }
}
```