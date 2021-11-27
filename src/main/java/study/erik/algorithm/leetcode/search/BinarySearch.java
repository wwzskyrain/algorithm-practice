package study.erik.algorithm.leetcode.search;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;

/**
 * @author erik.wang
 * @date 2020-08-22 19:36
 */
public class BinarySearch {

    /**
     * 基本的二分查找
     *
     * @param nums   有序无重复元素的数组
     * @param target target
     * @return 如果包含target，就返回target的index，否则返回插入位置
     */
    public int basicBinarySearch(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int m;
        while (l <= h) {
            m = l + (h - l) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l;
    }

    @Test
    public void test_() {
        int[] nums = ArrayUtils.buildArray("1,3,5,7");
        Assert.assertEquals(0, basicBinarySearch(nums, 1));
        Assert.assertEquals(1, basicBinarySearch(nums, 3));
        Assert.assertEquals(3, basicBinarySearch(nums, 7));
        Assert.assertEquals(0, basicBinarySearch(nums, 0));
        Assert.assertEquals(1, basicBinarySearch(nums, 2));
        Assert.assertEquals(4, basicBinarySearch(nums, 8));
    }

    /**
     * 最左边的那个元素的index
     *
     * @param nums   升序数组(重复元素)
     * @param target target
     * @return 最左边的那个元素的index-(假设一定能找到)
     */
    public int findFirstTarget(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int m;
        while (l <= h) {
            m = l + (h - l) / 2;
            if (nums[m] == target) {
                // 注意这里，
                h = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l;
    }

    @Test
    public void test_findFirstTarget() {
        int[] nums = ArrayUtils.buildArray("1");
        int[] nums1 = ArrayUtils.buildArray("1,1,1");
        int[] nums2 = ArrayUtils.buildArray("1,1,1,2");
        int[] nums3 = ArrayUtils.buildArray("1,2,3,3,3,3,5");
        Assert.assertEquals(0, findFirstTarget(nums, 1));
        Assert.assertEquals(0, findFirstTarget(nums1, 1));
        Assert.assertEquals(0, findFirstTarget(nums2, 1));
        Assert.assertEquals(2, findFirstTarget(nums3, 3));
    }

    public int findLastTarget(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int m;
        while (l <= h) {
            m = l + (h - l) / 2;
            if (nums[m] == target) {
                // 注意这里，
                l = m + 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return h;
    }

    @Test
    public void findLastTarget() {
        int[] nums = ArrayUtils.buildArray("1");
        int[] nums1 = ArrayUtils.buildArray("1,1,1");
        int[] nums2 = ArrayUtils.buildArray("1,1,1,2");
        int[] nums3 = ArrayUtils.buildArray("1,2,3,3,3,3,5");
        Assert.assertEquals(0, findLastTarget(nums, 1));
        Assert.assertEquals(2, findLastTarget(nums1, 1));
        Assert.assertEquals(2, findLastTarget(nums2, 1));
        Assert.assertEquals(5, findLastTarget(nums3, 3));
    }

    /**
     * 小于等于target的最大值
     *
     * @param nums   nums
     * @param target target
     * @return 小于等于target的最大值
     */
    public int findLessThanAndEqualToTarget(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int m;
        while (l <= h) {
            m = l + (h - l) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                // m+1不能越界，而且
                if (m + 1 < nums.length && nums[m + 1] <= target) {
                    l = m + 1;
                } else {
                    return l;
                }
            } else {
                h = m - 1;
            }
        }
        throw new RuntimeException("不会走到这里");
    }

    @Test
    public void test_findLessThanAndEqualToTarget() {
        int[] nums = ArrayUtils.buildArray("2,4");
        Assert.assertEquals(0, findLessThanAndEqualToTarget(nums, 2));
        Assert.assertEquals(0, findLessThanAndEqualToTarget(nums, 3));
        Assert.assertEquals(1, findLessThanAndEqualToTarget(nums, 4));
        Assert.assertEquals(1, findLessThanAndEqualToTarget(nums, 5));
    }

}
