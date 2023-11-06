package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;
import study.erik.algorithm.leetcode.tree.BinaryTreeTraversalSolutions.Node;

import java.util.*;

/**
 * 日期：2023/11/3 09:32
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Populating_Next_Right_Pointers_in_Each_Node_II {

    @LetCodeCommit(title = "117. Populating Next Right Pointers in Each Node II",
            selfRemark = "这个题目还是比较有意思的。" +
                    "不用队列也能完成bfs，哈哈哈。" +
                    "其实这个题目是变相的，也就是说，我们在做bfs的时候，其实也可以用next指针来完成队列的工作。")
    public Node connect(Node root) {
        Node cur = root;
        while (cur != null) {
            Node curTemp = cur;
            // 头结点：表示下一行的头结点
            Node headNode = new Node();
            Node nextLine = headNode;
            while (curTemp != null) {
                if (curTemp.left != null) {
                    nextLine.next = curTemp.left;
                    nextLine = nextLine.next;
                }
                if (curTemp.right != null) {
                    nextLine.next = curTemp.right;
                    nextLine = nextLine.next;
                }
                curTemp = curTemp.next;
            }
            cur = headNode.next;
        }
        return root;
    }

}
