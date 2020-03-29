package study.erik.algorithm.leetcode.greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-22 18:14
 * @description
 */
public class GasStationTest {

    /**
     * url = https://leetcode.com/problems/gas-station/submissions/
     * title = gas station
     * extension=
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        return solution2(gas, cost);
    }

    /**
     * 计算分析思路：由于油箱体积很大，所以每个站点的油都可以累积起来。
     * 所以，如果只要 路上的汽油总数大于耗费总数，就能过得去，否则必然过不去，返回-1吧。
     * 所以，下面讨论过得去时的情况
     * case1：大大的过的去，比如每一个站点都有足够整条路耗油之和，那么就随便返回一个加油站小标即可。
     *      然而题意已经限制了，只会有唯一的解。所以就要看case2
     * case2：恰好过的去。加入从0加油站往前走，走到i处，断掉了，即过不到i+1了。
     *      说明从0,1,2,...,i出发都会断掉，至少会断在i处。
     *      所以，如果从0左边的某个结点出发呢？如果从i+1点出发呢？这虽然是两个方向，但是因为这一条
     *      环路，所以其实是一个方向。所以就讨论后一种情况——从i+1点出发——也是唯一的方向。
     *      如果从i+1出发之后，又在k出断掉了，那参考case1，说明i+1，i+2，...，k都不是要找的
     *      出发点。但是我们已经判断了，总油量是大于总耗油的，所以必定会过去的，所以我们只要一直往下
     *      找，就一定能找到能够start的出发点。
     * 出自[leetcode gas-station(Java)](https://blog.csdn.net/zangdaiyang1991/article/details/89425686)
     * 成绩：100% + 5.88%（这个可能是我上一次提交退了后退）
     * @param gas
     * @param cost
     * @return
     */
    public int solution2(int[] gas, int[] cost) {
        int rest = 0, run = 0, start = 0;
        for (int i = 0; i < gas.length; ++i) {
            run += (gas[i] - cost[i]);
            rest += (gas[i] - cost[i]);
            if (run < 0) {
                // 不断的更新start，最后一个就是要找的解。
                start = i + 1;
                run = 0;
            }
        }
        return rest < 0 ? -1 : start;
    }

    /**
     * 一遍过了，而成绩很低：7%和5.88%
     * 解法：两层循环。
     * 模拟思路：这是一个模拟题，只需要按照题意操作，然后判断结果就好了。然而成绩很低，
     *  所以就还有一种计算思路，见{@link #solution2(int[], int[])}
     * @param gas
     * @param cost
     * @return
     */
    public int solution1(int[] gas, int[] cost) {

        for (int start = 0; start < gas.length; start++) {
            int index = start;
            int curGas = 0;
            do {
                curGas = curGas + gas[index];
                if (curGas < cost[index]) {
                    break;
                }
                curGas = curGas - cost[index];

                index = (index + 1) % gas.length;
                if (index == start) {
                    return index;
                }
            } while (true);
        }
        return -1;
    }


    @Test
    public void test_solution() {

        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        Assert.assertEquals(3, canCompleteCircuit(gas, cost));

        int[] gas1 = {2, 3, 4};
        int[] cost1 = {3, 4, 3};
        Assert.assertEquals(-1, canCompleteCircuit(gas1, cost1));

    }

}
