package study.erik.algorithm.sword;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/21 ，时间：14:58
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Reverse_Pairs {

    @LetCodeCommit(title = "170. 交易逆序对的总数",
            selfRemark = "一看就知道是归并排序可以解决。" +
                    "然后动手写了一次。" +
                    "思路还是挺清晰的。" +
                    "除了手误，写错了一个变量名称，然后有个边界没有取等于，一调试也就出来了。")
    public int reversePairs(int[] record) {
        return mergeSort(record, 0, record.length - 1);
    }

    public int mergeSort(int[] record, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = (l + r) / 2;
        int total = 0;
        total += mergeSort(record, l, m);
        total += mergeSort(record, m + 1, r);
        int i = l, j = m + 1;
        int[] newSegment = new int[r - l + 1];
        int idx = 0;
        while (i <= m && j <= r) {
            newSegment[idx++] = Math.min(record[i], record[j]);
            if (record[i] <= record[j]) {
                i++;
                //出现逆序对
                total += (j - m - 1);
            } else {
                j++;
            }
        }
        while (i <= m) {
            newSegment[idx++] = record[i++];
            //计算逆序
            total += (j - m - 1);
        }
        while (j <= r) {
            newSegment[idx++] = record[j++];
        }
        System.arraycopy(newSegment, 0, record, l, newSegment.length);
        return total;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray("[1,2,3,1,3]")},
                {8, ArrayUtils.buildArray("[9, 7, 5, 4, 6]")},
                {4, ArrayUtils.buildArray("[1,3,2,3,1]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] record;

    @Test
    public void test() {
        Assert.assertEquals(expect, reversePairs(record));
    }

}
