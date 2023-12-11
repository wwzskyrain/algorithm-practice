package study.erik.algorithm.job.software.simulation03;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/9 07:40
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int findDuplicate(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            if (nums[i] < 0) {
                continue;
            }
            int n = nums[i];
            int curIdx = i;
            while (n != curIdx + 1) {
                if (nums[n - 1] < 0) {
                    // case1:闭环
                    if (n - 1 == i) {
                        break;
                    } else {
                        // case2:已经是环了
                        return n;
                    }
                }
                if (nums[n - 1] == n) {
                    //人家已经OK了
                    return n;
                }
                nums[curIdx] = -n;
                curIdx = n - 1;
                n = nums[curIdx];
            }
            if (n == i + 1) {
                nums[curIdx] = -n;
            }
        }
        return -1;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray("2,1,2")},
                {2, ArrayUtils.buildArray("[1,3,4,2,2]")},
                {3, ArrayUtils.buildArray("[3,1,3,4,2]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertEquals(expect, findDuplicate(nums));
    }

}
