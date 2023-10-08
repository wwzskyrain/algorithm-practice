package study.erik.algorithm.leetcode.zijie.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.function.Predicate;

/**
 * 日期：2023/10/8 ，时间：14:08
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Number_of_Tasks_You_Can_Assign {

    @LetCodeCommit(title = "2071. Maximum Number of Tasks You Can Assign",
            selfRemark = "这个题目有点难，但是难点不在于不好想，而在于贪心算法证明。" +
                    "先说一下正常思路，把最小的任务分给最大力量的人呗。这一点可以看力扣的官方解答。" +
                    "当然，这个算法也是来自于力扣的官方解答：https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/solutions/1101831/ni-ke-yi-an-pai-de-zui-duo-ren-wu-shu-mu-p7dm/?company_slug=huawei" +
                    "")
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int n = tasks.length, m = workers.length;
        Arrays.sort(tasks);
        Arrays.sort(workers);

        /**
         * 贪心策略（这里不证明）：
         * 针对最小的m个任务从大到小一个一个的干掉，如果有一个不能干掉，则这个目标——m——就是不能完成的。
         * 在从大到小便利的过程，每一个都是当前最大的任务。
         * 策略：1.能不吃药就不吃药；
         * 2.不吃药能搞定，就让最大力气那个工人搞定，反正留着他也没有更大的任务给他了。
         * 3.不吃药搞不定的话，那就让吃药后刚好能搞定的那个工人搞定该任务，这时候要留着更大力气的工人，因为他可能搞定后面的小任务。即使他搞不定，它还有吃药的机会
         */
        Predicate<Integer> check = (mid) -> {
            int p = pills;
            Deque<Integer> ws = new ArrayDeque<>();
            int ptr = m - 1;
            // 从大到小枚举每一个任务，每一个任务都要完成的，不然这个mid个任务就不是可以完成的。就要缩小二分查找的范围。
            for (int i = mid - 1; i >= 0; --i) {
                while (ptr >= m - mid && workers[ptr] + strength >= tasks[i]) {
                    //这里是插队，插到对头，保存的是那些吃药可以完成第i个任务(也是当前最大的任务)的工人。
                    // 工人力气从大到小——队尾的力气最大。
                    ws.addFirst(workers[ptr]);
                    --ptr;
                }
                if (ws.isEmpty()) {
                    //吃药也完不成第i个任务，那就没戏了。
                    return false;
                }
                // 如果双端队列中最大的元素大于等于 tasks[i]——
                // case1：不吃药就能干掉第i个任务。这时候就不吃药，让最大力气那个干活，反正留着他也没用了，后面的任务更小了。
                else if (ws.peekLast() >= tasks[i]) {
                    ws.removeLast();
                } else {
                    if (p == 0) {
                        return false;
                    }
                    --p;
                    //case2:不吃药搞不定了，那就让吃药后刚好能搞定的那个工人搞定该任务，这时候要留着更大力气的工人
                    ws.removeFirst();
                }
            }
            return true;
        };

        int left = 1, right = Math.min(m, n), ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check.test(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray("[3,2,1]"), ArrayUtils.buildArray("[0,3,3]"), 1, 1},
                {1, ArrayUtils.buildArray("[5,4]"), ArrayUtils.buildArray("[0,0,0]"), 1, 5},
                {2, ArrayUtils.buildArray("[10,15,30]"), ArrayUtils.buildArray("[0,10,10,10,10]"), 3, 10},
                {3, ArrayUtils.buildArray("[5,9,8,5,9]"), ArrayUtils.buildArray("[1,6,4,2,6]"), 1, 5},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] tasks;
    @Parameterized.Parameter(2)
    public int[] works;
    @Parameterized.Parameter(3)
    public int pill;
    @Parameterized.Parameter(4)
    public int strength;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxTaskAssign(tasks, works, pill, strength));
    }

}
