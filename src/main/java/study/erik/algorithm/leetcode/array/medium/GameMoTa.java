package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/2/6 23:03
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class GameMoTa {

    @LetCodeCommit(title = "LCP 30. 魔塔游戏",
            selfRemark = "贪心解法：官网的这个算法的解释，很拙劣。贪心策略都不说。")
    public int magicTower(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int ans = 0;
        long hp = 1, delay = 0;
        for (int num : nums) {
            if (num < 0) {
                pq.offer(num);
            }
            hp += num;
            if (hp <= 0) {
                ++ans;
                int curr = pq.poll(); //贪心策略就是把最亏血的放假往后移动。
                delay += curr;
                hp -= curr;
            }
        }
        hp += delay;
        return hp <= 0 ? -1 : ans;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray("100,100,100,-250,-60,-140,-50,-50,100,150")},
                {-1, ArrayUtils.buildArray("[-200,-300,400,0]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, magicTower(nums));
    }

}
