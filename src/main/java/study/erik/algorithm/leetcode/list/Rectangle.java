package study.erik.algorithm.leetcode.list;

import org.junit.Assert;
import org.junit.Test;


import java.util.*;

public class Rectangle {


    @Test
    public void test_largestRectangleArea() {
        int[] heights = {1,1,1,1,1,2, 1, 5, 6, 2, 3};
        int result = largestRectangleArea2(heights);
        Assert.assertEquals(11, result);
    }

    public int largestRectangleArea(int[] heights) {

        int largestArea = 0;
        for (int i = 0; i < heights.length; i++) {

            int low = i;
            int high = i;
            while (low - 1 >= 0 && heights[low - 1] >= heights[i]) {
                low--;
            }

            while (high + 1 < heights.length && heights[high + 1] >= heights[i]) {
                high++;
            }

            largestArea = Math.max((high - low + 1) * heights[i], largestArea);
        }

        return largestArea;
    }

    public int largestRectangleArea2(int[] heights) {

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();

        List<Integer> heightList = new ArrayList<>();
        for (int height : heights) {
            heightList.add(height);
        }
        heightList.add(0);

        for (int i = 0; i < heightList.size(); ++i) {
            while (!stack.isEmpty() && heightList.get(stack.peek()) >= heightList.get(i)) {
                int cur = stack.pop();

                int area = heightList.get(cur) * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }

}
