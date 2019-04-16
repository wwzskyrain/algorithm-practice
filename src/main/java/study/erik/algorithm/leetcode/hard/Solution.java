package study.erik.algorithm.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author erik.wang
 * @date 2019/03/23
 **/
public class Solution {

    /**
     * 857. Minimum Cost to Hire K Workers From N
     *
     * @param quality 表示能力值；大家的工资是成比与这个能力值的
     * @param wage    每位工人的最小薪水
     * @param K       目标工人个数。
     * @return 返回最小的K个工人的薪水总数<br                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               />
     * 提前分析：1.最简单的思路是求一下N对K的组合，然后计算每个组合实例的薪水总数，求最小值。
     * 再分析：组合求最值的方式是太low了，也是没有找到题目中的暗含的"趋势变化"条件。
     */
    public double minCostToHireWorkers(int[] quality, int[] wage, int K) {

        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < quality.length; ++i)
            workers[i] = new double[]{(double) (wage[i]) / quality[i], (double) quality[i]};
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE;
        double qsum = 0;
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>();
        for (double[] worker : workers) {
            qsum += worker[1];
            priorityQueue.add(-worker[1]);
            if (priorityQueue.size() > K) qsum += priorityQueue.poll();
            if (priorityQueue.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;

//      排序
//      按照数组值给index排序

    }

    @Test
    public void test_minCostToHireWorkers() {

        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int k = 2;
        double exceptCost = 105;
        double realCost = minCostToHireWorkers(quality, wage, k);
        System.out.println(exceptCost + " = " + realCost);

        quality = new int[]{3, 1, 10, 10, 1};
        wage = new int[]{4, 8, 2, 2, 7};
        k = 3;
        exceptCost = 105;
        realCost = minCostToHireWorkers(quality, wage, k);
        System.out.println(exceptCost + " = " + realCost);

    }

}
