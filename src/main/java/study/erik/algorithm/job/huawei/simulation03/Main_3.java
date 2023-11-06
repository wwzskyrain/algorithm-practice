package study.erik.algorithm.job.huawei.simulation03;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/3 10:34
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {


    @LetCodeCommit(title = "",
            selfRemark = "这是一个hard题目，题目“监控二叉树”，其实我们做过的，可惜竟然忘了。" +
                    "毕竟题目有点难，需要分析的逻辑比较多。而且，这是一个基于贪心的最值题目，可以参加lee215的解法。" +
                    "我们错在哪里：首先，理解就错了，比如第一个测试用例，我们的理解必须要3个啊，可是只需要2个。" +
                    "那就是在第2和第4位置放摄像机，就能监控6个啦。")
    /**
     * {@link study.erik.algorithm.leetcode.tree.BinaryTreeCameras#camera }
     */
    public int minCameraCover(TreeNode root) {
        int flag = place1(root);
        return (flag < 1 ? 1 : 0 ) + cameraNum;
    }

    int cameraNum = 0;

    /**
     * 0:叶子结点
     * 1:camera节点
     * 2:被子节点覆盖了
     *
     * @param root
     * @return
     */
    public int place1(TreeNode root) {
        if (root == null) {
            return 2;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int left = place1(root.left);
        int right = place1(root.right);
        if (left == 0 || right == 0) {
            cameraNum++;
            return 1;
        }
        if (left == 1 || right == 1) {
            return 2;
        }
        return 0;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, TreeNode.buildTree("[0,1,null,null,2,3,null,null,4,5]")},
                {3, TreeNode.buildTree("[0,0,null,0,0,0,null,null,0]")},
                {2, TreeNode.buildTree("[0,0,0,null,null,null,0]")},
                {2, TreeNode.buildTree("[0,1,null,2,null,3,null,null,4]")},
                {1, TreeNode.buildTree("[0,1,null,2,3]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public TreeNode root;

    @Test
    public void test() {
        Assert.assertEquals(expect, minCameraCover(root));
    }

}
