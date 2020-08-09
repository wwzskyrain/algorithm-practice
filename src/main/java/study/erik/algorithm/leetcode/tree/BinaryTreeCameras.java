package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-05 11:12
 */
public class BinaryTreeCameras {

    @LetCodeCommit(no = 968, title = "Binary Tree Cameras", diff = "h", time = 53, space = 6,
            types = {LetCodeCommit.Type.Tree, LetCodeCommit.Type.Greedy},
            selfRemark = "不会做，看的lee大神的结点，思想很好，多看一遍，学会这样思考",
            extend = "Distribute Coins in Binary Tree")
    public int minCameraCover(TreeNode root) {
        return (post(root) < 1 ? 1 : 0) + camera;
    }

    public int camera = 0;

    /**
     * 返回root结点的状态
     * 0:root是叶子结点
     * 1:root结点是叶子结点的父节点，也就是camera结点
     * 2:root结点被其子节点camera覆盖了
     *
     * @param root
     * @return
     */
    public int post(TreeNode root) {

        if (root == null) {
            return 2;
        }

        if (root.left == null && root.right == null) {
            // 叶子结点
            return 0;
        }
        int left = post(root.left);
        int right = post(root.right);
        if (left == 0 || right == 0) {
            //有一个孩子是叶子孩子
            camera++;
            return 1;
        }

        if (left == 1 || right == 1) {
            //被孩子节点camera覆盖了
            return 2;
        }
        //左右叶子结点都是2，也就是都被覆盖了，就要(逻辑)删除子节点了，删除后该节点也就是(逻辑)叶子结点了
        return 0;
    }

    @Test
    public void test_case_1() {
        TreeNode root = TreeNode.buildTree("0,0,null,0,0");
        Assert.assertEquals(1, minCameraCover(root));
    }

    @Test
    public void test_case_2() {
        TreeNode root = TreeNode.buildTree("0,0,null,0,null,0,null,null,0");
        Assert.assertEquals(2, minCameraCover(root));
    }


}
