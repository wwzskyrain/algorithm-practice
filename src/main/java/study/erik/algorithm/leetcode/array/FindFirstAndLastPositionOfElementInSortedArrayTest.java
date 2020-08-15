package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;


/**
 * @author erik.wang
 * @date 2020-08-10 17:52
 */
public class FindFirstAndLastPositionOfElementInSortedArrayTest {


    @LetCodeCommit(no = 3704, title = "Find First and Last Position of Element in Sorted Array",
            time = 100, timeMillisecond = 0, space = 93, types = LetCodeCommit.Type.BinarySearch,
            selfRemark = "复习一下binary-search"
    )
    public int[] searchRange(int[] nums, int target) {

        int index = standardBinarySearch(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int pre = index - 1;

        while (pre >= 0 && nums[pre] == target) {
            pre--;
        }

        int next = index + 1;
        int length = nums.length;
        while (next < length && nums[next] == target) {
            next++;
        }
        return new int[]{pre + 1, next - 1};
    }


    public int standardBinarySearch(int[] nums, int target) {

        int l = 0;
        int h = nums.length - 1;

        while (l <= h) {
            int mid = (l + h) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;

    }

    @Test
    public void test_case_1() {
        Assert.assertArrayEquals(new int[]{3, 4}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));

    }

    @Test
    public void test_case_2() {
        Assert.assertArrayEquals(new int[]{-1, -1}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
    }

    @Test
    public void test_case_3() {
        Assert.assertArrayEquals(new int[]{-1, -1}, searchRange(new int[]{}, 0));
    }

    @Test
    public void test_case_4() {
        Assert.assertArrayEquals(new int[]{0, 0}, searchRange(new int[]{1}, 1));
    }
}
