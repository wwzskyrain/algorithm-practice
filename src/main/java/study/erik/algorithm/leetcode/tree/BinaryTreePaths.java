package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-09-04 08:36
 */
public class BinaryTreePaths {

    @LetCodeCommit(title = "257. Binary Tree Paths",
            selfRemark = "以后在使用到Stack的场景中，可以直接用LinkList，不要用Deque了。"
                    + "哈哈，还可以优化，如果知道栈的最大深度，还可以用数组",
    related = {"Remove Invalid Parentheses"})
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        solutionWithPreOrder(root, "", paths);
        return paths;
    }

    public void solutionWithPreOrder(TreeNode root, String path, List<String> paths) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            //发现叶子结点啦
            paths.add(path + root.val);
            return;
        }

        solutionWithPreOrder(root.left, path + root.val + "->", paths);
        solutionWithPreOrder(root.right, path + root.val + "->", paths);

    }


    @Test
    public void test_solution_1() {
        TreeNode treeNode = TreeNode.buildTree("1,2,3,null,5,null,null");
        List<String> paths = binaryTreePaths(treeNode);
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
