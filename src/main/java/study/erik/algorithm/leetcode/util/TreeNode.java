package study.erik.algorithm.leetcode.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class TreeNode {

    public int      val;
    public TreeNode left  = null;
    public TreeNode right = null;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof TreeNode)) {return false;}
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                (left != null && left.equals(treeNode.left)) &&
                (right != null && right.equals(treeNode.right));
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    public static TreeNode buildTree(List<Integer> values) {
        TreeNode[] treeNodeArray = new TreeNode[values.size() + 1];

        for (int i = 0; i < values.size(); i++) {
            treeNodeArray[i] = new TreeNode(values.get(i));
        }

        return doBuild(treeNodeArray);
    }

    /**
     * 广度有效遍历法来构造二叉树
     *
     * @param input
     * @return
     */
    public static TreeNode buildTree(String input) {

        if (input == null || input.trim().length() == 0) {
            return null;
        }
        input = input.replace("[", "");
        input = input.replace("]", "");
        String[] splitStr = input.split(",");
        TreeNode[] tree = new TreeNode[splitStr.length + 1];
        int i = 0;
        for (String ele : splitStr) {
            if (ele.trim().equals("null")) {
                tree[i++] = null;
            } else {
                tree[i++] = new TreeNode(Integer.valueOf(ele.trim()));
            }
        }

        return doBuild(tree);
    }

    private static TreeNode doBuild(TreeNode[] tree) {

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(tree[0]);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            if (i > tree.length - 1) {
                break;
            }
            if (tree[i] != null) {
                queue.addLast(tree[i]);
            }
            node.left = tree[i];
            i++;

            if (i > tree.length - 1) {
                break;
            }
            if (tree[i] != null) {
                queue.addLast(tree[i]);
            }
            node.right = tree[i];
            i++;
        }
        return tree[0];
    }

    public static void main(String[] args) {
        TreeNode tree = buildTree("4,8,null,6,1,9,null,-5,4,null,null,null,-3,null,10");
        System.out.println(tree);

        TreeNode tree1 = buildTree("1,4,3,2,4,2,5,null,null,null,null,null,null,4,6");
        System.out.println(tree1);

        TreeNode tree2 = TreeNode.buildTree("3, 5, 1, 6, 2, 0, 8, null, null, 7, 4");
        System.out.println(tree2);
    }
}