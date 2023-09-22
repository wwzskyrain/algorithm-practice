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
 * 日期：2023/9/17 ，时间：15:30
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Smallest_Range_Covering_Elements_from_K_Lists {

    @LetCodeCommit(title = "632. Smallest Range Covering Elements from K Lists")
    public int[] smallestRange(List<List<Integer>> nums) {
        List<Pair<Integer, Integer>> sortNums = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (Integer n : nums.get(i)) {
                sortNums.add(new Pair<>(n, i));
            }
        }
        int n = nums.size();
        int[] counter = new int[n];
        sortNums.sort(Comparator.comparingInt(Pair::getKey));
        int i = 0;
        int c = 0;
        int[] ans = null;
        //这里并没有对所有的有效窗口做比较操作，而是对部分窗口——不用担心这部分窗口中不包含最有解。
        for (int j = 0; j < sortNums.size(); j++) {
            //统计每一个以j结尾的有效窗口，且最优的有效窗口——做边界最小。
            int preCount = counter[sortNums.get(j).getValue()]++;
            if (preCount == 0) {
                c++;
            }
            if (c == n) {//c不可能大于n的。
                //有效窗口来了。
                while (counter[sortNums.get(i).getValue()] > 1) {
                    counter[sortNums.get(i).getValue()]--;
                    i++;
                }
                //优化窗口——左边界最小。
                if (ans == null || sortNums.get(j).getKey() - sortNums.get(i).getKey() < ans[1] - ans[0]) {
                    ans = new int[]{sortNums.get(i).getKey(), sortNums.get(j).getKey()};
                }
            }
        }
        return ans;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildList2Dimension("[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]")},
                {ArrayUtils.buildList2Dimension("[[1,2,3],[1,2,3],[1,2,3]]")}
        });
    }


    @Parameterized.Parameter
    public List<List<Integer>> nums;

    @Test
    public void test() {
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

}
