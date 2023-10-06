package study.erik.algorithm.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/4 ，时间：17:10
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Buildings_With_an_Ocean_View {

    @LetCodeCommit(title = "1762. Buildings With an Ocean View",
    selfRemark = "哎呦，不是medium吗，竟然这么简单。Facebook的题目都太简单了。")
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        boolean[] canSee = new boolean[n];
        int curMaxHeight = 0;
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (heights[i] > curMaxHeight) {
                canSee[i] = true;
                count++;
                curMaxHeight = heights[i];
            }
        }
        int[] ret = new int[count];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (canSee[i]) {
                ret[idx++] = i;
            }
        }
        return ret;
    }

}
