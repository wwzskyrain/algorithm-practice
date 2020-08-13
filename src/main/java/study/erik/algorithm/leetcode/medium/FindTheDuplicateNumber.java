package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-13 21:02
 */
public class FindTheDuplicateNumber {


    @LetCodeCommit(no = 287, title = "Find the Duplicate Number")
    public int findDuplicate(int[] nums) {

        int s = nums[0];
        int f = nums[0];
        do {
            s = nums[s];
            f = nums[nums[f]];
        } while (f != s);
        //走出while, f和s就相遇了

        s = nums[0];
        while (s != f) {
            //记住，这时候，s、f都是value，也是下一个元素的指针，也就是下一个元素的唯一标识。
            //不要用do-while形式了，s==f的话，表示他俩指向了相同的index，也就是第一个环的入口点。
            //而且他俩一样了哎，那就是他俩了。
            s = nums[s];
            f = nums[f];
        }
        return s;

    }

    @Test
    public void test_case_1() {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        Assert.assertEquals(2, findDuplicate(nums));
    }

    @Test
    public void test_case_2() {
        int[] nums = new int[]{3, 1, 3, 4, 2};
        Assert.assertEquals(3, findDuplicate(nums));
    }

}
