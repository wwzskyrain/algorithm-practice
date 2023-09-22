package study.erik.algorithm.leetcode.array.hard;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/9/17 ，时间：17:38
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class The_Skyline_Problem {

    @LetCodeCommit(title = "218. The Skyline Problem")
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<Pair<Integer, Integer>> points = new ArrayList<>();
        for (int[] building : buildings) {
            points.add(new Pair<>(building[0], -building[2]));
            points.add(new Pair<>(building[1], building[2]));
        }
        points.sort((o1, o2) -> !Objects.equals(o1.getKey(), o2.getKey()) ? o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue());
        //排序节点：从左当有，高度从低到高

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);    //大顶堆
        queue.add(0); // 这个是为最后一个点准备的
        int preHeight = 0;
        List<List<Integer>> ans = new ArrayList<>();
        for (Pair<Integer, Integer> point : points) {
            int x = point.getKey();
            int height = point.getValue();
            if (height < 0) {
                queue.add(-height);
            } else {
                queue.remove(height);
            }
            Integer curHigh = queue.peek();
            if (preHeight != curHigh) {
                List<Integer> p = new ArrayList<>();
                p.add(x);
                p.add(curHigh);
                ans.add(p);
            }
            preHeight = curHigh;
        }
        return ans;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{
                ArrayUtils.buildArray2Dimension("[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]")},
                {ArrayUtils.buildArray2Dimension("[[0,2,3],[2,5,3]]")},});
    }

    @Parameterized.Parameter
    public int[][] buildings;

    @Test
    public void test() {
        System.out.println(getSkyline(buildings));
    }

}
