package study.erik.algorithm.leetcode.list;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import static study.erik.algorithm.util.QuestionType.Link_List;

/**
 * @author erik.wang
 * @date 2020-08-13 12:51
 */
public class CopyListWithRandomPointer {


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    @LetCodeCommit(no = 138, title = "Copy List with Random Pointer", time = 100, space = 35, diff = "m",
            types = Link_List,
            selfRemark = "复制和拆分",
            related = {"Clone Binary Tree With Random Pointer", "Clone N-ary Tree"})
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }
        Node p = head;

        while (p != null) {
            Node copyNode = new Node(p.val);
            copyNode.next = p.next;
            p.next = copyNode;
            p = copyNode.next;
        }

        p = head;
        Node pp = head.next;
        while (pp != null) {
            pp.random = (p.random == null) ? null : p.random.next;
            if (pp.next == null) {
                break;
            }
            p = p.next.next;
            pp = pp.next.next;
        }

        p = head;

        pp = head.next;
        Node newHead = pp;

        while (pp != null) {
            p.next = pp.next;
            p = p.next;
            pp.next = (pp.next == null) ? null : pp.next.next;
            pp = pp.next;
        }

        return newHead;
    }


    @Test
    public void test_case_1() {
        String input = "[[7,null],[13,0],[11,4],[10,2],[1,0]]";
        Node copyNode = copyRandomList(buildInput(input));
        System.out.println(copyNode);

    }

    public Node buildInput(String input) {
        input = input.replace("[", "");
        input = input.replace("]", "");
        String[] nodesPairs = input.split(",");
        Node[] nodes = new Node[nodesPairs.length / 2];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(Integer.valueOf(nodesPairs[2 * i]));
        }

        for (int i = 0; i < nodes.length; i++) {
            if (i < nodes.length - 1) {
                nodes[i].next = nodes[i + 1];
            }
            String str = nodesPairs[2 * i + 1];
            if (!str.equals("null")) {
                nodes[i].random = nodes[Integer.valueOf(str)];
            }
        }
        return nodes.length == 0 ? null : nodes[0];
    }

}
