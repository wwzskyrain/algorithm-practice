package study.erik.algorithm.job.zijie.simulation02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/6 08:58
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Map<Integer, Integer> reverse = new HashMap<>();
        for (int[] description : descriptions) {
            int p = description[0];
            int c = description[1];
            reverse.put(c, p);
            TreeNode pNode = map.get(p);
            if (pNode == null) {
                pNode = new TreeNode(p);
                map.put(p, pNode);
            }

            TreeNode cNode = map.get(c);
            if (cNode == null) {
                cNode = new TreeNode(c);
                map.put(c, cNode);
            }
            if (description[2] == 1) {
                pNode.left = cNode;
            } else {
                pNode.right = cNode;
            }
        }
        int root = descriptions[0][0];
        Integer p;
        while ((p = reverse.get(root)) != null){
            root = p;
        }
        return map.get(root);
    }

}
