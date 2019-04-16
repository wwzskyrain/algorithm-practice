package leetcode.mediu;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {
/*

实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

注意: next() 和hasNext() 操作的时间复杂度是O(1)，并使用 O(h) 内存，其中 h 是树的高度。
* */


/*

    提交总结：使用Deque要比Stack要快很多。比如4ms（100%）和6ms
*/

    Deque<TreeNode> stack = new ArrayDeque<TreeNode>();

    public BSTIterator(TreeNode root) {

        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }

    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (!stack.isEmpty()) {

            TreeNode minNode = stack.pop();
            TreeNode p = minNode.right;

            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            return minNode.val;
        }
        return -1;
    }

    public static void main(String[] args) {
        BSTIterator bstIterator = new BSTIterator(null);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}