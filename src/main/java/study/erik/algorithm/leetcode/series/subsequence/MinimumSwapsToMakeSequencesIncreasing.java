package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-05 12:37
 */
public class MinimumSwapsToMakeSequencesIncreasing {

    public int minSwap(int[] A, int[] B) {
        return solution2(A, B);
    }

    /**
     * 通过了，成绩100% 和87%
     * 分析，这是一道很难的题目了，虽然只是medium难度。
     * 解法是双dp法。也是我第一次理解并采用的方法。
     * 首先可以用dfs来接着道题目，估计会超时。一会试一下，因为思路非常简单。果然超时了。
     * 该思路是来自帖子：Grandyang https://www.cnblogs.com/grandyang/p/9311385.html
     * 但是他的分析和他的代码我觉得都有不妥支持。下面我只记录自己的代码
     * 首先声明：这个这是提交的第二版，把数组换成了长度为2。第一版swap和noSwap都是和A一样规模的。方便起见，还是按照swap.length=A.length来讲。
     * 定义swap[i]表示，A[i]、B[i]不交换就能是A[0..i]和B[0..i]都成为自增数组的最小交换次数；
     * 而noSwap[i]表示，A[i]、B[i]要交换能是A[0..i]和B[0..i]都成为自增数组的最小交换次数；
     * 这样定义，是基于子问题的。那么原问题具有子问题性质吗？当然有，而且很明显，这里不赘述。
     * <p>
     * 下面开始分析
     * case1: A[i-1]<A[i] & B[i-1]<B[i]
     * case2: A[i-1]<A[i] & B[i-1]>=B[i]
     * case3: A[i-1]>=A[i] & B[i-1]<B[i]
     * case4: A[i-1]>=A[i] & B[i-1]>=B[i]
     * <p>
     * case1时，A[i]和B[i]可以不交换，这是 noSwap[i] = noSwap[i-1];
     * 也可以交换，这时有分情况：是否A[i-1]、B[i-1]也要交换
     * 1.不交换，条件是A[i]>B[i-1] && B[i]>A[i-1]即交叉递增了，此时 swap[i]=noSwap[i]+1
     * 2.交换，没有上面那个约束，因为这时候相当于两个位置都没有交换
     * <p>
     * case2和case3 都是不得不交换的。交换之后就一定能保证SA[i]>A[i-1] SB[i]>B[i-1]吗？因为SA[i]其实就是B[i],SB[i]=A[i]，
     * 所以交换之前就能看出交换之后是否AB都递增；那就要求A[i]>B[i-1]并且B[i]>A[i-1]。会这样吗？
     * case4？请放心，不可能出现case4的情况，因为不可能通过交换使双递减变成双递增。这个用反证法，通过不等式的传递性就能完成证明。
     * <p>
     * 回到case2和case3，会那样吗？答，会的，因为这就是题设的一部分。
     *
     * @param A
     * @param B
     * @return
     */
    public int solution(int[] A, int[] B) {
        int[] swap = new int[2];
        int[] noSwap = new int[2];

        swap[0] = 1;
        noSwap[0] = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                swap[i % 2] = swap[(i - 1) % 2] + 1;
                noSwap[i % 2] = noSwap[(i - 1) % 2];
                if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                    swap[i % 2] = Math.min(swap[i % 2], noSwap[(i - 1) % 2] + 1);
                    noSwap[i % 2] = Math.min(noSwap[(i - 1) % 2], swap[(i - 1) % 2]);
                }
            } else {
                swap[i % 2] = noSwap[(i - 1) % 2] + 1;
                noSwap[i % 2] = swap[(i - 1) % 2];
            }
        }
        return Math.min(swap[(A.length - 1) % 2], noSwap[(A.length - 1) % 2]);
    }

    /**
     * 第一版双递归法则
     *
     * @param A
     * @param B
     * @return
     */
    public int solutin1(int[] A, int[] B) {
        int[] swap = new int[A.length];
        int[] noSwap = new int[B.length];

        swap[0] = 1;
        noSwap[0] = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                swap[i] = swap[i - 1] + 1;
                noSwap[i] = noSwap[i - 1];
                if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                    swap[i] = Math.min(swap[i], noSwap[i - 1] + 1);
                    noSwap[i] = Math.min(noSwap[i - 1], swap[i - 1]);
                }
            } else {
                swap[i] = noSwap[i - 1] + 1;
                noSwap[i] = swap[i - 1];
            }
        }
        return Math.min(swap[A.length - 1], noSwap[A.length - 1]);
    }

    public int solution2(int[] A, int[] B) {
        return dfs(A, B, 1, 0);
    }

    /**
     * dfs解法果然超时了
     *
     * @param a
     * @param b
     * @param index
     * @param count
     * @return
     */
    public int dfs(int[] a, int[] b, int index, int count) {
        if (index == a.length) {
            return count;
        }
        int tempCount = Integer.MAX_VALUE;
        if (a[index] > a[index - 1] && b[index] > b[index - 1]) {
            tempCount = Math.min(tempCount, dfs(a, b, index + 1, count));
        }
        if (a[index] > b[index - 1] && b[index] > a[index - 1]) {
            int temp = a[index];
            a[index] = b[index];
            b[index] = temp;
            tempCount = Math.min(tempCount, dfs(a, b, index + 1, count + 1));
            temp = a[index];
            a[index] = b[index];
            b[index] = temp;
        }
        return tempCount;
    }

    @Test
    public void test_solution() {

        int[] A1 = {0, 4, 4, 5, 9}, B1 = {0, 1, 6, 8, 10};
        Assert.assertEquals(1, minSwap(A1, B1));

        int[] A = {1, 3, 5, 4}, B = {1, 2, 3, 7};
        Assert.assertEquals(1, minSwap(A, B));
    }
}
