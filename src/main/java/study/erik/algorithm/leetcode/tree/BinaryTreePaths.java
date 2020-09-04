package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-09-04 08:36
 */
public class BinaryTreePaths {

    @LetCodeCommit(title = "257. Binary Tree Paths",
            selfRemark = "以后在使用到Stack的场景中，可以直接用LinkList，不要用Deque了")
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if (root != null) {
            LinkedList<Integer> path = new LinkedList<>();
            solution(root, path, paths);
        }
        return paths;
    }

    public void solution(TreeNode root, LinkedList<Integer> currentPath, List<String> paths) {

        if (root.left == null && root.right == null) {
            //发现叶子结点啦
            StringBuilder sb = new StringBuilder();
            Iterator<Integer> iterator = currentPath.descendingIterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next()).append("->");
            }
            sb.append(root.val);
            paths.add(sb.toString());
            return;
        }
        if (root.left != null) {
            currentPath.push(root.val);
            solution(root.left, currentPath, paths);
            currentPath.pop();
        }

        if (root.right != null) {
            currentPath.push(root.val);
            solution(root.right, currentPath, paths);
            currentPath.pop();
        }
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
