package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-25 13:13
 */
public class FindAllNumbersDisappearedinAnArray {


    @LetCodeCommit(title = "448. Find All Numbers Disappeared in an Array",
            related = "Find All Duplicates in an Array")
    public List<Integer> findDisappearedNumbers(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            while (nums[temp - 1] != temp && temp != i + 1) {
                int t = nums[temp - 1];
                nums[temp - 1] = temp;
                temp = t;
            }
            nums[i] = temp;
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public List<Integer> findDisappearedNumbersI(int[] nums) {
//      谁出现了，就把谁-作为下标-标记一下，这里就是设置为负值，算是出现过了。
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] > 0) {
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
            }
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }


    @Test
    public void test_solution_1() {
        Assert.assertEquals(Arrays.asList(5, 6), findDisappearedNumbersI(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }


    @Test
    public void test_solution_2() {
        Assert.assertEquals(Arrays.asList(), findDisappearedNumbersI(new int[]{2, 1}));
    }


}
