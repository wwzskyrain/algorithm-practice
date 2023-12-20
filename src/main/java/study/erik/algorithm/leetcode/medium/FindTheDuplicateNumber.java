package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-13 21:02
 */
@RunWith(Parameterized.class)
public class FindTheDuplicateNumber {


    @LetCodeCommit(no = 287, title = "Find the Duplicate Number",
            selfRemark = "快慢指针，真是一个好算法。" +
                    "这个题目我前几天也做过，当时硬是用")
    public int findDuplicate(int[] nums) {
        //快慢指针的有效性证明：
        //对于i>0，假设nums[i] = i，则nums[i]是自我闭合的。否则，可以看做是一个链表节点了。
        //对于i>0，最多有n个环(每一个nums[i]都自我闭合)，最少也要有一个环：可能每个元素都在环上，也可能在环之前有一段链
        //因为nums[i]>0，所以0这个位置是不可能被指的，所以从0这个位子出发，一定能进入到某一个环中。
        /*
        s和f都是地址，即下标。该算法是标准的快慢指针
        * */
        int s = 0;
        int f = 0;
        do {
            s = nums[s];
            f = nums[nums[f]];
        } while (s != f);

        f = 0;
        while (s != f) {
            s = nums[s];
            f = nums[f];
        }
        // 到这里时，求出了环的入口位置(下标)。但是，该位置上的值，即nums[s]并不是重复元素，
        // 反而s本身是重复元素，因为有两个地方指向了该位置，分别是环内和环外。
        return s;
    }

    public int findDuplicate1(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i || nums[i] < 0) {
                // case1:自闭环
                // case2:已经访问过
                continue;
            }
            int j = i;
            while (nums[j] != i) {
                //如果nums[j] == i,说明找到一个环，从i开始的环。OK，继续下一个i吧。
                int n = nums[j];
                nums[j] = -n;
                if (nums[n] == n || nums[n] < 0) {
                    // case1：nums[j] = n, nums[n] = n, 所以n出现了两次
                    // case2：nums[n]， 访问过啦，所以n出现了两次
                    return n; //返回前注意把负值都转换成正直.
                } else {
                    j = n;
                }
            }
        }
        return -1;
    }


    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {2, ArrayUtils.buildArray("1, 3, 4, 2, 2")},
                {3, ArrayUtils.buildArray("3, 1, 3, 4, 2")},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, findDuplicate1(nums));
    }
}
