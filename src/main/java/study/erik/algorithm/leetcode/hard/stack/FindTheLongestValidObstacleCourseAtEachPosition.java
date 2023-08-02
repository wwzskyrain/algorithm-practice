package study.erik.algorithm.leetcode.hard.stack;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

@RunWith(Parameterized.class)
public class FindTheLongestValidObstacleCourseAtEachPosition {

    @LetCodeCommit(title = "1964. Find the Longest Valid Obstacle Course at Each Position",
            diff = "h",
            selfRemark = "这个题目很不错呀，适合面试。" +
                    "这个题好像做过的。")
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] ans = new int[n];
        int top = -1;
        //stack[i]表示有一个subsequence，它的长度是i+1，它的结尾值是stack[i]=obstacle
        //根据定义可以知道，stack是一个非严格的单调递增数组，因为假设stack[i]=o1<stack[j]=o2，
        //则o2可以后缀到o1上形成更长的子序列subsequence，从而i<j
        int[] stack = new int[n];
        for (int i = 0; i < obstacles.length; i++) {
            if (top == -1 || obstacles[i] >= stack[top]) {
                stack[++top] = obstacles[i];
                ans[i] = top + 1;
            } else {
                int index = binarySearch(stack, 0, top, obstacles[i]);
                stack[index] = obstacles[i];
                ans[i] = index + 1;
            }
        }
        return ans;
    }

    public int binarySearch(int[] stack, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (stack[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high + 1;
    }

}
