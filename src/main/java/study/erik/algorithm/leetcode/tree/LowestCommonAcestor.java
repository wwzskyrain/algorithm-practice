package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

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

    /**
     * 非递归版本，需要适用后续遍历，stack + lastVisitedNode
     * todo 吧
     *
     * @param root
     * @param targetNode
     * @return
     */
    public LinkedList<TreeNode> findPath(TreeNode root, TreeNode targetNode) {

        Deque<TreeNode> stack = new LinkedList<>();

        // TODO: 2020-08-15  
        return new LinkedList<>();
    }


    /**
     * 递归版本的findPath
     *
     * @param root
     * @param targetNode
     * @param path
     * @return 递归版本的findPath
     */
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
     * <p>
     * 下面还有一个递归解法。
     * 这个递归解法是很巧妙的。可是我却解释不通，算了吧。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2I(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor(root, p, q);
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return 返回包含了p或者q结点的最近结点。not null表示p或者q在这个在这个root树中，
     * 当left子树返回不空并且right子树返回不空时，当前root就是最近公共祖先，一路传上去。
     */
    @LetCodeCommit(no = 236, title = "Lowest Common Ancestor of a Binary Tree",
            selfRemark = "著名的最近公共祖先问题，这里是一个递归解法。上次面试美团就要求这种解法，我竟然不会；")
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

        TreeNode root = TreeNode.buildTree("3, 5, 1, 6, 2, 0, 8, null, null, 7, 4");
        LinkedList<TreeNode> pathToP = new LinkedList<>();
        findPath(root, new TreeNode(1), pathToP);


        System.out.println(pathToP.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
        LinkedList<TreeNode> path = findPath(root, new TreeNode(1));
        System.out.println(path.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
    }

    @Test
    public void test_lowestCommonAncestor2() {
        List<Integer> input = Arrays.asList();
        TreeNode root = TreeNode.buildTree("3,5,1,6,2,0,8,null,null,7,4");

        TreeNode commonAncestor = lowestCommonAncestor2(root, new TreeNode(5), new TreeNode(1));
        Assert.assertEquals("3", commonAncestor.val);

    }


}
