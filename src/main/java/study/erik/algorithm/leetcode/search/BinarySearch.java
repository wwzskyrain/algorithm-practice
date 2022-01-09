package study.erik.algorithm.leetcode.search;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.MatcherUtils;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 找到num的插入位置，如果重复，插入到第一个重复元素的位置
     * 也可以看第二个版本
     * 注意两个版本的区别
     * 1.当结束条件是left<=right时，right的初始值是size，而且返回位置去right
     * 2.当结束条件是left<right时，right的初始值是size-1，而且返回位置去left
     * 3.至于当重复时，放到重复元素的后面还是前面，在处理valueM = num时处理即可
     *
     * @param list list
     * @param num  num
     * @return 插入位置
     * @see BinarySearch#findInsertIndex2(List, Integer)
     */
    public int findInsertIndex1(List<Integer> list, Integer num) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Integer valueM = list.get(mid);
            if (valueM < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 找到num的插入位置，如果重复，插入到第一个重复元素的位置
     * 也可以看第二个版本
     *
     * @param list
     * @param num
     * @return
     */
    public int findInsertIndex2(List<Integer> list, Integer num) {
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            Integer valueM = list.get(mid);
            if (valueM < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * 有重复元素时，放最后面
     *
     * @param list
     * @param num
     * @return
     */
    public int findInsertIndex3(List<Integer> list, Integer num) {
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            Integer valueM = list.get(mid);
            if (valueM <= num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * 有重复元素时，放最后面
     *
     * @param list
     * @param num
     * @return
     */
    public int findInsertIndex4(List<Integer> list, Integer num) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Integer valueM = list.get(mid);
            if (valueM <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @Test
    public void test_findInsertIndex3() {
        List<Integer> list = Arrays.asList(1);

        Assert.assertThat(0, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 0)),
                new IsEqual<>(findInsertIndex4(list, 0))));

        Assert.assertEquals(1, findInsertIndex3(list, 1));
        Assert.assertEquals(1, findInsertIndex4(list, 1));

        Assert.assertThat(1, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 1)),
                new IsEqual<>(findInsertIndex4(list, 1))));

        Assert.assertThat(1, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 2)),
                new IsEqual<>(findInsertIndex4(list, 2))
        ));

        list = Arrays.asList(1, 3);
        Assert.assertThat(0, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 0)),
                new IsEqual<>(findInsertIndex4(list, 0))));

        Assert.assertThat(1, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 1)),
                new IsEqual<>(findInsertIndex4(list, 1))));

        Assert.assertThat(1, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 2)),
                new IsEqual<>(findInsertIndex4(list, 2))));

        Assert.assertThat(2, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 3)),
                new IsEqual<>(findInsertIndex4(list, 3))));

        Assert.assertThat(2, CoreMatchers.allOf(
                new IsEqual<>(findInsertIndex3(list, 4)),
                new IsEqual<>(findInsertIndex4(list, 4))));

    }

    @Test
    public void test_findInsertIndex1() {
        List<Integer> list = Arrays.asList(1);
        Assert.assertThat(0, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 0),
                findInsertIndex2(list, 0)));

        Assert.assertThat(0, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 1),
                findInsertIndex2(list, 1)));

        Assert.assertThat(1, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 2),
                findInsertIndex2(list, 2)));

        list = Arrays.asList(1, 3);
        Assert.assertThat(0, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 1),
                findInsertIndex2(list, 1)));

        Assert.assertThat(1, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 2),
                findInsertIndex2(list, 2)));
        Assert.assertThat(1, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 3),
                findInsertIndex2(list, 3)));
        Assert.assertThat(2, MatcherUtils.allOfEquals(
                findInsertIndex1(list, 4),
                findInsertIndex2(list, 4)));

    }

}
