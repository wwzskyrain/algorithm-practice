package study.erik.algorithm.leetcode.other;

import org.junit.Test;

import java.util.*;

/**
 * @author erik.wang
 * @date 2019/04/24
 **/
public class NSum {

    /**
     * title = Two Sum
     * 1.只有一个解的，放心
     * 2.不能重复使用一个元素
     * 解答：1.先排序，再search
     * 注意事项：这个方法中使用了二维数组来保存排序前元素的下标，并且通过自定义Comparator来排序和查找
     * 解答：2.还有更好的方法，就是使用HashMap，直接用它来完成查找 见 {@link NSum#twoSumI2(int[], int)}
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumI1(int[] nums, int target) {

        int[][] dataAndIndex = new int[nums.length][];

        for (int i = 0; i < nums.length; i++) {
            dataAndIndex[i] = new int[]{nums[i], i};
        }


        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };

        Arrays.sort(dataAndIndex, comparator);

        for (int i = 0; i < dataAndIndex.length; i++) {

            int index = Arrays.binarySearch(dataAndIndex,
                    new int[]{target - dataAndIndex[i][0], 0},
                    comparator);

            if (index >= 0 && index != i) {

                int[] result = new int[]{dataAndIndex[i][1], dataAndIndex[index][1]};
                Arrays.sort(result);
                return result;
            }
        }

        throw new IllegalArgumentException();

    }

    /**
     * title : Two Sum
     * 文不加点，以便通过
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumI2(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);//假设元素不重复
        }

        for (int i = 0; i < nums.length; i++) {

            Integer index = map.get(target - nums[i]);
            if (index != null && !index.equals(i)) {
                if (index.compareTo(i) > 0) {
                    return new int[]{i, index};
                } else {
                    return new int[]{index, i};
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @Test
    public void test_2_sum_I() {

        int[] nums = new int[]{3, 2, 3};
        System.out.println(Arrays.toString(twoSumI2(nums, 6)));

    }


    /**
     * title = Two Sum II - Input array is sorted
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumII1(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {

            int foundIndex = Arrays.binarySearch(numbers, target - numbers[i]);

            if (foundIndex > 0 && foundIndex != i) {

                if (foundIndex > i) {
                    return new int[]{i + 1, foundIndex + 1};
                } else if (foundIndex > 0) {
                    return new int[]{foundIndex + 1, i + 1};
                }

            }

        }

        throw new IllegalArgumentException();
    }

    @Test
    public void test_twoSumII1() {
        int[] num = new int[]{2, 7, 11, 15};

        System.out.println(Arrays.toString(twoSumII1(num, 9)));
    }


}
