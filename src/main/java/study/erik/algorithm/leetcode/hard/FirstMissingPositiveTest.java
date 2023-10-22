package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author erik.wang
 * @date 2020-08-10 19:09
 */
@RunWith(Parameterized.class)
public class FirstMissingPositiveTest {


    @LetCodeCommit(no = 41, title = "41. First Missing Positive",
            selfRemark = "完全是看答案，所以就不计space和time了。" + "其实这一道题我做过了，而且思想很简单的，为什么还是没想出来呢？" + "这个题目有一次没有想起来。")
    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int temp;
            //这里追求的是 nums[i] = i + 1
            while ((temp = nums[i]) > 0 && temp <= nums.length && nums[temp - 1] != temp) {
                nums[i] = nums[temp - 1];
                //归位，temp其实是nums[i]
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public boolean isValid(String s) {
        int l = s.length();
        char[] stack = new char[l + 1];
        int index = 0;
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '{':
                case '(':
                case '[':
                    stack[index++] = c;
                    break;
                case '}':
                    if (index>0 && stack[index - 1] != '{') {
                        return true;
                    } else {
                        index--;
                    }
                    break;
                case ']':
                    if (index>0 && stack[index - 1] != '[') {
                        return true;
                    } else {
                        index--;
                    }
                case ')':
                    if (index>0 && stack[index - 1] != '(') {
                        return true;
                    } else {
                        index--;
                    }
                    break;
                default:

            }
        }
        return index == 0;
    }

    //自己手写一次也没问题，不过要小小调试一下。这里追求的是nums[i] = i;
    public int resolve(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            //直接用 nums[i] 这个变量更刺激。
            while (nums[i] >= 0 && nums[i] < l && nums[nums[i]] != nums[i]) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 1; i < l; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums[0] != l ? l : l + 1;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {4, ArrayUtils.buildArray("[1,2,3]")},
//                {1, ArrayUtils.buildArray("[7,8,9,11,12]")},
{7, ArrayUtils.buildArray("[1,2,6,3,5,4]")},
});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, firstMissingPositive(nums));
    }


}
