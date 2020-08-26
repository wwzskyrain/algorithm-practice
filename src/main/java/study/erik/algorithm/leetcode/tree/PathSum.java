package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

import static study.erik.algorithm.util.QuestionType.Tree;

/**
 * @author erik.wang
 * @date 2019/06/10
 **/
public class PathSum {

    /**
     * title = Path Sum
     * 描述：是否存在一条路径，是的路径和为sum。
     *
     * @param root
     * @param sum
     * @return
     */
    @LetCodeCommit(no = 112, title = "Path Sum", types = Tree)
    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }
        //注意要到叶子结点才算
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);

    }

    /**
     * title = Path Sum II
     * 描述：找到所有路径和是sum的路径
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSumII(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> currentPath = new LinkedList<>();
        pathSum(root, sum, currentPath, result);

        return result;

    }

    /**
     * 以当前节点为二叉树，以当前路径为基础，找寻目标路径
     *
     * @param root
     * @param sum
     * @param currentPath
     * @param result
     */
    public void pathSum(TreeNode root, int sum, Deque<Integer> currentPath, List<List<Integer>> result) {

        if (root == null) {
            return;
        }

        currentPath.push(root.val);
        if (root.right == null && root.left == null) {  // leaf node
            if (root.val == sum) {
                ArrayList<Integer> resultPath = new ArrayList<>(currentPath);
                Collections.reverse(resultPath);
                result.add(resultPath);
            }
        }

        pathSum(root.left, sum - root.val, currentPath, result);
        pathSum(root.right, sum - root.val, currentPath, result);

        currentPath.pop();
    }


    /**
     * Path Sum III
     * 描述:找到路径和是sum的路径的个数；路径有新定义：联系的从上到下的节点，开头节点不许是root，结尾节点不需要是叶子结点。
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumIII(TreeNode root, int sum) {

        doPathSumIII(root, sum, new ArrayList<>());
        return counter;
    }

    public Integer counter = 0;

    /**
     * 自己想了一个路子；就是用一个List来保存从root到当前节点的路径sum；在加上check逻辑，就好了
     * 评价：这个方法也算个方法，但是性能不佳。
     * 其实，对于求路径和啊，一定要用'随深度递减sum'的套路。看下面的解；
     *
     * @param root
     * @param sum
     * @param currentPathSums
     */
    public void doPathSumIII(TreeNode root, int sum, List<Integer> currentPathSums) {

        if (root == null) {
            return;
        }
        Integer lastPathSum = 0;
        if (currentPathSums.size() != 0) {
            lastPathSum = currentPathSums.get(currentPathSums.size() - 1);
        }
        Integer currentPathSum = lastPathSum + root.val;
        currentPathSums.add(currentPathSum);
        if (currentPathSum == sum) {
            counter++;
        }
        //check
        for (int i = 0; i < currentPathSums.size() - 1; i++) {
            if (currentPathSum - currentPathSums.get(i) == sum) {
                counter++;
            }
        }
        doPathSumIII(root.left, sum, currentPathSums);
        doPathSumIII(root.right, sum, currentPathSums);
        currentPathSums.remove(currentPathSums.size() - 1);
    }

    @Test
    public void test_pathSum() {

        List<Integer> data = Arrays.asList(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        TreeNode root = TreeNode.buildTree(data);
        Assert.assertEquals(3, pathSumIII2(root, 22));

    }

    /**
     * 使用两重递归来解答
     * 内递归是求出以root为路径起始节点的满足pathSum=sum的路径树
     * 外递归则是对root中的没有个结点都做内递归。
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumIII2(TreeNode root, int sum) {

        return doPathSumIII2(root, sum);
    }

    @Test
    public void test_pathSumIII2() {

        TreeNode root = TreeNode.buildTree("5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1");
        Assert.assertEquals(3, pathSumIII2(root, 22));

    }

    /**
     * 函数功能：root中，有多少"以root为起始节点的路径的的和为sum"，这里的路径不一定要以叶子结点结束。
     * 这是一个递归函数，在解题思路中属于内递归。
     *
     * @param root
     * @param sum
     * @return
     */
    public int hasPathSum1(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }
        int pathCount = 0;
        if (root.val == sum) {
            pathCount++;
        }

        int leftCount = hasPathSum1(root.left, sum - root.val);
        int rightCount = hasPathSum1(root.right, sum - root.val);
        return pathCount + leftCount + rightCount;
    }

    /**
     * 先序遍历二叉树，也是一个递归算法；在解题思路上属于外递归
     *
     * @param root
     * @param sum
     * @return
     */
    public int doPathSumIII2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
//        int ret = hasPathSum(root, sum) + pathSumIII2(root.left, sum) + pathSumIII2(root.right, sum);

        int ret = 0; // 先处理了
        return ret;
    }


}
