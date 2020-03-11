package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-03-11 17:28
 * @description
 */
public class Triangle {

    /**
     * titile =
     * url = https://leetcode.com/problems/triangle/submissions/
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return solution(triangle);
    }

    /*
        成绩：20% 和 8% ，成绩很差。
        解法分析：解法并不是自己想出来的——其实无所谓是不是自己想出来的了，因为我自己本来就是个空白，
        一切知识都是学来的。
        正题：感觉不应该，因为并没有使用额外的空间，而且解答也是很紧凑的，应该都近似100%才对。

     */
    public int solution(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int minSum = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j);
                triangle.get(i).set(j, minSum);
            }
        }
        return triangle.get(0).get(0);
    }

    @Test
    public void test_solution() {

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));

        Assert.assertEquals(11, solution(triangle));
    }

}
