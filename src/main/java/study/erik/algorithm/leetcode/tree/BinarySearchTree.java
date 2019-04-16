package study.erik.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/04/07
 **/
public class BinarySearchTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * title = Unique Binary Search Trees
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {

        int[] nums = new int[n + 1];

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        nums[0] = 1;
        nums[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                nums[i] += nums[j] * nums[i - 1 - j];
            }
        }
        return nums[n];
    }

    /**
     * title=Unique Binary Search Trees II
     * 这是一个典型的"分治法"
     * 返回的是一个二叉树；而leetcode对树有自己的打印方式，无序去把他"扁平化"
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }
        return generateSubTree(1, n);
    }

    public List<TreeNode> generateSubTree(int start, int end) {

        List<TreeNode> result = new LinkedList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {

            List<TreeNode> left = generateSubTree(start, i - 1);
            List<TreeNode> right = generateSubTree(i + 1, end);

            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {

                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    root.right = right.get(k);
                    result.add(root);
                }
            }

        }
        return result;
    }

    /**
     * title = Validate Binary Search Tree
     * 解法：中序遍历，前驱 > root 则不是"二叉搜索树"
     * 时间和空间都不高
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pointer = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || pointer != null) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {

                TreeNode pop = stack.pop();

                if (pre != null && pre.val >= pop.val) {
                    return false;
                }
                pre = pop;
                pointer = pop.right;
            }
        }
        return true;

    }

    /**
     * title = Validate Binary Search Tree
     *
     * @param root
     * @return
     */
    public boolean isValidBSTI(TreeNode root) {

        return doValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 这个解法没有问题的，就是边界值搞不定，边界值是"Integer.MAX_VALUE","Integer.MIN_VALUE".
     *
     * @param root
     * @param lower 对当前节点的最小约束
     * @param upper 对当前节点的最大约束
     *              层层向下，具有传递性的。
     *              最左边的节点，它的限制区间就是(-M,中序遍历后继节点的val)
     *              最右边节点，它的限制区间就是(中序遍历前驱节点的val，M);
     *
     *              思路解析：我本来想的是，获取左子树的最大值，获取右子树的最小值，比较当前节点的val要在这两个之间。
     *              而这个解法是反方向走的：以当前节点为出发点，下放它左子树的最大值，下放它有字数的最小值。
     *              这里下放一词有歧义，似乎是在左右子树是的。其实就是的。
     *              根节点的取值区间并不是（左子树的最大值，右子树的最小值），而是（Min，Max）。
     *              其实各节点的取值区间无所谓，因为它是出发点。
     *
     * @return
     */
    public boolean doValid(TreeNode root, int lower, int upper) {

        if (root == null) {
            return true;
        }

        return root.val > lower && root.val < upper
                && doValid(root.left, lower, root.val)
                && doValid(root.right, root.val, upper);
    }


    /**
     * title = Convert Sorted Array to Binary Search Tree
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        return convert(nums, 0, nums.length - 1);
    }

    public TreeNode convert(int[] nums, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = convert(nums, start, mid - 1);
        root.right = convert(nums, mid + 1, end);

        return root;
    }


    public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

    /**
     * title = Convert Sorted List to Binary Search Tree
     * 不同的是，输入为'链表'，而不是数组
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
// TODO: 2019/4/7  跟上一题没区别
        return null;
    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MIN_VALUE - 1);
    }


}
