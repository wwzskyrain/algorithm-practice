/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : Move.java, v 0.1 2021年05月16日 11:23 上午 yueyi Exp $
 */
public class Move {

    public static void main(String[] args) {
        long star = System.currentTimeMillis();
        int plateNum = 21;
        LinkedList<Integer> plates = new LinkedList<>();
        for (int i = 1; i <= plateNum; i++) {
            plates.addFirst(i);
        }
        Plate plateA = new Plate("A", plates);

        Plate plateB = new Plate("B", new LinkedList<>());

        Plate plateC = new Plate("C", new LinkedList<>());

        move(plateA.plates.size(), plateA, plateB, plateC);

        long end = System.currentTimeMillis();
        System.out.printf("挪%d个盘子用了%d秒\n", plateNum, (end - star) / 1000);

    }

    /**
     *
     * @param i
     * @param plateA
     * @param plateB
     * @param plateC
     */
    public static void move(int i, Plate plateA, Plate plateB, Plate plateC) {
        if (i == 2) {
            int p = plateA.removeLast();
            plateB.addLast(p);
            System.out.printf("[%s].%d --> %s\n", plateA.name, p, plateB);

            p = plateA.removeLast();
            plateC.addLast(p);
            System.out.printf("[%s].%d --> %s\n", plateA.name, p, plateC);

            p = plateB.removeLast();
            plateC.addLast(p);
            System.out.printf("[%s].%d --> %s\n", plateB.name, p, plateC);
            return;
        }
        move(i - 1, plateA, plateC, plateB);

        int topA = plateA.removeLast();
        plateC.addLast(topA);
        System.out.printf("[%s].%d --> %s\n", plateA.name, topA, plateC);

        move(i - 1, plateB, plateA, plateC);
    }

    public static class Plate {
        public String         name;
        public Deque<Integer> plates;

        @Override
        public String toString() {
            return String.format("[%s].%s", name, plates.toString());
        }

        public Plate(String name, Deque<Integer> plates) {
            this.name = name;
            this.plates = plates;
        }

        public int removeLast() {
            return plates.removeLast();
        }

        public void addLast(Integer p) {
            plates.addLast(p);
        }
    }

}