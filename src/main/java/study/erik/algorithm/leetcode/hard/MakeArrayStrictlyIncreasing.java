package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-04-03 23:04
 */
public class MakeArrayStrictlyIncreasing {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        return solution(arr1, arr2);
    }

    public int solution(int[] arr1, int[] arr2) {
        if (arr1.length < 2) {
            return 0;
        }
        Arrays.sort(arr2);
        boolean[] visited = new boolean[arr2.length];
        int[] stack = new int[arr1.length];
        int minCount = dfs(arr1, arr2, visited, stack, 0, 0);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private int dfs(int[] arr1, int[] arr2, boolean[] visited, int[] stack, int index, int count) {
        if (index == arr1.length) {
            return count;
        }
        int tempCount = Integer.MAX_VALUE;
        if (index == 0 || arr1[index] > stack[index - 1]) {
            stack[index] = arr1[index];
            tempCount = Math.min(tempCount, dfs(arr1, arr2, visited, stack, index + 1, count));
        }

        for (int i = 0; i < arr2.length; i++) {
            if (index == 0 || (arr2[i] > stack[index - 1] && !visited[i])) {
                stack[index] = arr2[i];
                visited[i] = true;
                tempCount = Math.min(tempCount, dfs(arr1, arr2, visited, stack, index + 1, count + 1));
                visited[i] = false;
            }
        }
        return tempCount;
    }


    @Test
    public void test_solution() {

        // 超时了
        int[] arr9 = {9, 18, 3, 8, 21, 6, 7, 2, 7, 28, 23, 16, 33, 2, 25, 14, 15}, arr10 = {13, 2, 15, 30, 31, 30, 9, 10, 7, 30, 31, 4, 33, 10, 25, 28, 19, 6, 15, 6, 19, 30, 25, 14, 7, 28, 23, 20, 1, 2, 25, 16};
        Assert.assertEquals(2, makeArrayIncreasing(arr9, arr10));


        // 经过了四组测试，算法算是没问题了，然后在第五组case中就超时了
        int[] arr7 = {5, 16, 19, 2, 1, 12, 7, 14, 5, 16}, arr8 = {6, 17, 4, 3, 6, 13, 4, 3, 18, 17, 16, 7, 14, 1, 16};
        Assert.assertEquals(8, makeArrayIncreasing(arr7, arr8));

        int[] arr5 = {1, 5, 3, 6, 7}, arr6 = {4, 3, 1};
        Assert.assertEquals(2, makeArrayIncreasing(arr5, arr6));

        int[] arr3 = {1, 5, 3, 6, 7}, arr4 = {1, 6, 3, 3};
        Assert.assertEquals(-1, makeArrayIncreasing(arr3, arr4));

        int[] arr1 = {1, 5, 3, 6, 7}, arr2 = {1, 3, 2, 4};
        Assert.assertEquals(1, makeArrayIncreasing(arr1, arr2));
    }
}
