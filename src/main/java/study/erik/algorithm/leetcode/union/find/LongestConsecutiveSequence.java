package study.erik.algorithm.leetcode.union.find;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-08-22 11:22
 */
public class LongestConsecutiveSequence {

    @LetCodeCommit(title = "128. Longest Consecutive Sequence")
    public int longestConsecutive(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] parent = new int[nums.length];
        int[] height = new int[nums.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.keySet().contains(num)) {
                continue;
            }
            map.put(num, i);
            Integer e = map.get(num + 1);
            if (e != null) {
                union(e, i, parent, height);
            }
            e = map.get(num - 1);
            if (e != null) {
                union(e, i, parent, height);
            }
        }
        int[] count = new int[nums.length];
        int max = 0;
        for (int i : parent) {
            int root = find(i, parent);
            count[root]++;
            max = Math.max(max, count[root]);
        }
        return max;
    }

    private int find(int e, int[] parent) {
        if (e != parent[e]) {
            parent[e] = find(parent[e], parent);
        }
        return parent[e];
    }

    private void union(int x, int y, int[] parent, int[] height) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);
        if (rootX == rootY) {
            return;
        }
        int hX = height[rootX];
        int hY = height[rootY];
        if (hX == hY) {
            height[rootX]++;
            parent[rootX] = rootY;
        } else if (hX < hY) {
            parent[rootX] = hY;
        } else {
            parent[rootY] = hX;
        }
    }


    @Test
    public void test_solution() {
        Assert.assertEquals(4, longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

}
