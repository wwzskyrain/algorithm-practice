package study.erik.algorithm.leetcode.sort1;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-09-07 08:21
 */
public class TopKFrequentElements {

    @LetCodeCommit(title = "347. Top K Frequent Elements", diff = "m",
            selfRemark = "topK frequent，思路应该是很简单的，先计数再排序；" +
                    "计数没办法就只能动用map了，而排序则可以用堆排序这种高效排序方法。" +
                    "回头自己写一写堆排序；优先级队里priorityQueue是什么鬼，一会看一下")
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.compute(num, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
        }
        int[][] count = new int[countMap.size()][];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            count[i++] = new int[]{entry.getKey(), entry.getValue()};
        }
        Arrays.sort(count, Comparator.comparing(o -> ((int[]) o)[1]).reversed());
        int[] result = new int[k];
        for (int i1 = 0; i1 < result.length; i1++) {
            result[i1] = count[i1][0];
        }
        return result;
    }

    @Test
    public void test_solution() {
        int[] nums = {2, 2, 3, 1, 1, 1};
        int k = 2;
        Assert.assertArrayEquals(new int[]{1, 2}, topKFrequent(nums, k));
    }

    @Test
    public void test_solution_1() {
        int[] nums = {1};
        int k = 1;
        Assert.assertArrayEquals(new int[]{1}, topKFrequent(nums, k));
    }

}
