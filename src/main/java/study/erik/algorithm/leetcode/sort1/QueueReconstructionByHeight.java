package study.erik.algorithm.leetcode.sort1;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-31 18:25
 */
public class QueueReconstructionByHeight {

    @LetCodeCommit(title = "406. Queue Reconstruction by Height", diff = "m",
            selfRemark = "这个题目挺有意思的，而该解法(看的diss区)也同样有意思；该解法中透露着一种思想：" +
                    "——还原事物本来的样子",
            related = "315. Count of Smaller Numbers After Self")
    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[0] != p2[0] ? p2[0] - p1[0] : p1[1] - p2[1];
            }
        });

        List<int[]> result = new LinkedList<>();
        for (int[] person : people) {
            result.add(person[1], person);
        }
        return result.toArray(new int[0][]);
    }

    @Test
    public void test_solution_1() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] expect = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};
        Assert.assertArrayEquals(expect, reconstructQueue(people));
    }


}
