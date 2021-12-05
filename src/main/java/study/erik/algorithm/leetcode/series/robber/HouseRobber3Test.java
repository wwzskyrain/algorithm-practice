package study.erik.algorithm.leetcode.series.robber;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * 系列总结：这是'打家劫舍'的系列总结。这个系列的题目很有意思，特别是这个题目翻译。
 * 而且它的解法也很优美，其难度梯度也很自然。
 * 但是从思想上看，它只包含了一个朴素的一维递归。
 * 该题包含了动态规划——我不知道什么叫dp了
 * 第一题：是一个简单的一维递归
 * 第二题：有点向二位递归的趋势，然而我们很简单的把它分解成了两个第一题。
 * 第三题：变成了树形而已，在第一题的基础上，考察了对二叉树的遍历。
 *
 * @author erik.wang
 * @date 2020-03-29 08:36
 */
public class HouseRobber3Test {

    @LetCodeCommit(title = "337. House Robber III",
            selfRemark = "不用备忘录会超时了")
    public int rob(TreeNode root) {
        return solution(root, new HashMap<>());
    }

    public int solution(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        Integer ret = map.get(root);
        if (ret != null) {
            return ret;
        }
        int noCurrentRoot = solution(root.left, map) + solution(root.right, map);

        int left = 0;
        if (root.left != null) {
            left = solution(root.left.left, map) + solution(root.left.right, map);
        }
        int right = 0;
        if (root.right != null) {
            right = solution(root.right.left, map) + solution(root.right.right, map);
        }
        ret = Math.max(root.val + left + right, noCurrentRoot);
        map.put(root, ret);
        return ret;
    }
}
