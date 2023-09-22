package study.erik.algorithm.leetcode.array;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/9/18 ，时间：20:45
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1240. Tiling a Rectangle with the Fewest Squares")
public class Tiling_a_Rectangle_with_the_Fewest_Squares {

    private Map<String, Integer> bestCountForSkyline = new HashMap<>();

    int lowestCountSoFar = Integer.MAX_VALUE;

    /**
     * 这个题目有两个解法。
     * 解法1：dp+trick。
     * dp的思路是，横切或者竖切，一刀切到底，这样可以很容易写递归。但是这个dp定义不使完全的解，在n<11,m<13的问题域中有一个特例。然而把这个特例特殊处理一下即可AC。
     * 总结解法1：也是一种思维价值。
     * <p>
     * 解法2：搜索了。dfs了
     * 搜索思路：按照当前的skyline，找到最小的那一块，试着放一个尽量大的瓷砖去覆盖它，从而产生子问题——新的skyline。（或者用回溯，或者用copy新数组，都可以往下递归。）
     * 在问题域比较小的时候，比如本题的问题域，可以搞定。
     *
     * @param height
     * @param width
     * @return
     */
    public int tilingRectangle(int height, int width) {
        if (height == width) {
            return 1;
        }
        if (height > width) return tilingRectangle(width, height);
        dfs(width, height, new int[width + 1], 0);
        return lowestCountSoFar;
    }

    private void dfs(int rectWidth, int rectHeight, int[] skyline, int count) {
        // first prune branches - 剪枝，这个skyline没有希望了。
        if (count >= lowestCountSoFar) {
            return;
        }

        // if skyline is full check if we beat our current best then return
        boolean full = true;
        for (int i = 1; i <= rectWidth; i++) {
            if (skyline[i] != rectHeight) {
                full = false;
                break;
            }
        }
        //skyline其实是问题的局部解状态，从一块也没有填，到已经填满所有'条子'。
        if (full) {
            lowestCountSoFar = Math.min(lowestCountSoFar, count);
            return;
        }

        //备忘录加速。
        StringBuilder key = new StringBuilder();
        for (int col = 1; col <= rectWidth; col++) {
            key.append(skyline[col]);
        }
        Integer cachedCount = bestCountForSkyline.get(key.toString());
        if (cachedCount == null || count < cachedCount) {
            bestCountForSkyline.put(key.toString(), count);
        } else if (cachedCount != null && cachedCount <= count) {
            return;
        }

        // now we find the lowest point of our skyline. We will try to place squares to fill this point up
        int startingHeight = skyline[1];    //最小条子的高度
        int startingCol = 1;                //最小条子的index
        for (int col = 2; col <= rectWidth; col++) {
            if (skyline[col] < startingHeight) {
                startingHeight = skyline[col];
                startingCol = col;
            }
        }

        int maxSquareSize = 1;
        while (
                maxSquareSize + startingHeight + 1 <= rectHeight // the next square size will not exceeded the height of the rectangle
                        && maxSquareSize + startingCol <= rectWidth // the next square size will not exceeded the width of the rectangle
                        // the square being built of the next size does not collide with another square farther down the rectangle
                        && skyline[startingCol + maxSquareSize] == skyline[startingCol]
        ) {
            // we can safely test the square of the next size
            maxSquareSize += 1;
        }

        // now we test placing squares of the possible heights we generated from LARGEST to SMALLEST. This way we can trim a bunch of branches
        for (int nextSquareSize = maxSquareSize; nextSquareSize > 0; nextSquareSize--) {
            int[] nextSkyline = Arrays.copyOf(skyline, rectWidth + 1);
            for (int col = startingCol; col <= startingCol + (nextSquareSize - 1); col++) {
                nextSkyline[col] = nextSquareSize + startingHeight;
            }

            dfs(rectWidth, rectHeight, nextSkyline, count + 1);
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, 2, 3},
                {5, 5, 8},
                {6, 11, 13},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int m;

    @Test
    public void test() {
        Assert.assertEquals(expect, tilingRectangle(n, m));
    }

}
