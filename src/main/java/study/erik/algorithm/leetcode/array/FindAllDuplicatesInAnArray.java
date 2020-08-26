package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-25 15:21
 */
public class FindAllDuplicatesInAnArray {


    @LetCodeCommit(title = "442. Find All Duplicates in an Array",
            selfRemark = "" +
                    "各就各位法，然后看谁没有就位，或者位置上的数据不对，那就知道啦；" +
                    "还有一种神奇的写法，负值标记法")
    public List<Integer> findDuplicates(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            while (nums[temp - 1] != temp && temp != i + 1) {
                int t = nums[temp - 1];
                nums[temp - 1] = temp;
                temp = t;
            }
            nums[i] = temp;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    public List<Integer> findDuplicatesI(int[] nums) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(Math.abs(Math.abs(nums[i])));
            } else {
                nums[index] = -nums[index];
            }
        }
        return result;
    }


    @Test
    public void test_solution_1() {
        Assert.assertEquals(Arrays.asList(2, 3), findDuplicatesI(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals(Arrays.asList(10, 1), findDuplicatesI(new int[]{10, 2, 5, 10, 9, 1, 1, 4, 3, 7}));
    }

}
