/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import lombok.Getter;
import lombok.Setter;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : FlattenAMultilevelDoublyLinkedList.java, v 0.1 2022年02月22日 10:27 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "430. Flatten a Multilevel Doubly Linked List",
        space = 13,
        time = 100,
        selfRemark = "用非递归和递归，使用空间都是一样的。"
                + "用递归的时候，代码更简单优美一些。")
public class FlattenAMultilevelDoublyLinkedList {
    @Getter
    @Setter
    public static class Node {
        public int  val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "->" + ((this.next != null) ? this.next.toString() : "");
        }
    }

    @LetCodeCommit(title = "430. Flatten a Multilevel Doubly Linked List")
    public Node flattenWithStack(Node head) {

        if (head == null) {
            return null;
        }
        Deque<Node> stack = new LinkedList<>();
        Node headNode = new Node();
        Node curNode = headNode;
        Node p = head;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                // 1.访问
                p.prev = curNode;
                curNode = p;
                // 2.入栈
                stack.push(p);
                // 3.p执行左
                p = p.child;
                continue;
            }
            p = stack.pop().next;
        }
        while (curNode != headNode) {
            curNode.child = null;
            curNode.prev.next = curNode;
            curNode = curNode.prev;
        }
        Node newRoot = headNode.next;
        newRoot.prev = null;
        return newRoot;
    }

    /**
     * 返回
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node tail = head;
        Node tempNext = head.next;
        Node childFlatten = flatten(head.child);

        if (childFlatten != null) {
            head.child = null;
            tail.next = childFlatten;
            childFlatten.prev = tail;
            while (childFlatten.next != null) {
                // 哇塞 100%的好成绩呢。都是这个while的功劳。
                childFlatten = childFlatten.next;
            }
            tail = childFlatten;
        }

        Node nextFlatten = flatten(tempNext);
        if (nextFlatten != null) {
            tail.next = nextFlatten;
            nextFlatten.prev = tail;
        }
        return head;
    }

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        node1.next = node2;

        node2.prev = node1;
        node2.next = node3;

        node3.prev = node2;
        node3.next = node4;
        node3.child = node7;

        node4.next = node5;
        node4.prev = node3;

        node5.next = node6;
        node5.prev = node4;

        node6.prev = node5;

        node7.next = node8;

        node8.next = node9;
        node8.prev = node7;
        node8.child = node11;

        node9.next = node10;
        node9.prev = node8;

        node10.prev = node9;

        node11.next = node12;

        node12.prev = node11;

        FlattenAMultilevelDoublyLinkedList flatten = new FlattenAMultilevelDoublyLinkedList();
        Node flattenHead = flatten.flatten(node1);
        System.out.println(flattenHead);
    }

}