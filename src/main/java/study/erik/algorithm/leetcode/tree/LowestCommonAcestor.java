package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.tree.helper.BinaryHelper;
import study.erik.algorithm.leetcode.tree.helper.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2019/06/29
 **/
public class LowestCommonAcestor {


    /**
     * title 235. Lowest Common Ancestor of a Binary Search Tree
     * 描述:
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode pointer = root;
        TreeNode lowNode = p.val < q.val ? p : q;
        TreeNode highNode = p.val > q.val ? p : q;
        while (pointer != null) {
            if (pointer.val >= lowNode.val && pointer.val <= highNode.val) {
                return pointer;
            } else if (pointer.val > q.val) {
                pointer = pointer.left;
            } else {
                pointer = pointer.right;
            }
        }

        return null;
    }

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * 当时一颗普通的二叉树时，下面是一种非递归解法，当然也用到了递归遍历二叉树，
     * 不过只遍历了两次。
     * <p>
     * 下面还有一个递归解法。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        LinkedList<TreeNode> pathToP = new LinkedList<>();
        LinkedList<TreeNode> pathToQ = new LinkedList<>();
        findPath(root, p, pathToP);
        findPath(root, q, pathToQ);

        Iterator<TreeNode> pIterator = pathToP.iterator();
        Iterator<TreeNode> qIterator = pathToQ.iterator();

        TreeNode commonAncestor = root;
        while (pIterator.hasNext() && qIterator.hasNext()) {
            TreeNode pNext = pIterator.next();
            TreeNode qNext = qIterator.next();
            if (pNext.val == qNext.val) {
                commonAncestor = pNext;
            } else {
                break;
            }
        }
        return commonAncestor;

    }

    public LinkedList<TreeNode> findPath(TreeNode root, TreeNode targetNode, LinkedList<TreeNode> path) {

        if (root == null) {
            return new LinkedList<>();
        }
        path.addLast(root);
        if (root.val == targetNode.val) {
            return path;
        }
        LinkedList<TreeNode> leftPath = findPath(root.left, targetNode, path);
        if (!leftPath.isEmpty()) {
            return leftPath;
        }

        LinkedList<TreeNode> rightPath = findPath(root.right, targetNode, path);
        if (!rightPath.isEmpty()) {
            return rightPath;
        }

        path.pollLast();
        return new LinkedList<>();

    }

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * 当时一颗普通的二叉树时，下面是一种非递归解法，当然也用到了递归遍历二叉树，
     * 不过只遍历了两次。
     * <p>
     * 下面还有一个递归解法。
     *  这个递归解法是很巧妙的。可是我却解释不通，算了吧。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2I(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor(root, p, q);
    }

    /**
     * 解法：定义lowestCommonAncestor函数为
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode != null ? leftNode : rightNode;
    }

    @Test
    public void test_find_path() {
        List<Integer> input = Arrays.asList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode root = BinaryHelper.buildTree(input);
        LinkedList<TreeNode> pathToP = new LinkedList<>();
        findPath(root, new TreeNode(1), pathToP);
        System.out.println(pathToP.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
    }

    @Test
    public void test_lowestCommonAncestor2() {
        List<Integer> input = Arrays.asList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode root = BinaryHelper.buildTree(input);

        TreeNode commonAncestor = lowestCommonAncestor2(root, new TreeNode(5), new TreeNode(1));
        Assert.assertEquals("3", commonAncestor.val.toString());

    }


}
