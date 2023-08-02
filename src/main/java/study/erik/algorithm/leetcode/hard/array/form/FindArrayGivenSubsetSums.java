package study.erik.algorithm.leetcode.hard.array.form;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/8/2 ，时间：12:13
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class FindArrayGivenSubsetSums {

    @LetCodeCommit(title = "1982. Find Array Given Subset Sums", diff = "h", selfRemark = "这个题目挺不错的。这是一个构造数组题目。" + "1.递归思想：相对于n的sums1，n+1的sums2，是sum1的个数的两倍。具体参见https://leetcode.com/problems/find-array-given-subset-sums/solutions/1437739/recursive-disambiguation/")
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int[] res = new int[n];
        int p = 0;
        while (sums.length > 1) {
            int newL = sums.length / 2;
            int[] l = new int[newL];
            int[] r = new int[newL];
            int lp = 0;
            int rp = 0;
            int num = sums[1] - sums[0];
            boolean l_zero = false; //检查0是否落在左边
            for (int i = 0, j = 0; i < sums.length; i++) {
                if (sums[i] != Integer.MIN_VALUE) { //跳过
                    l_zero |= sums[i] == 0; //sums[i]就是要split到l的。
                    l[lp++] = sums[i];
                    r[rp++] = sums[i] + num;
                    //标记[跳过 sums[i] + num的 sum]
                    for (j = Math.max(j + 1, i + 1); sums[j] != sums[i] + num; ++j) ;
                    sums[j] = Integer.MIN_VALUE;
                }
            }
            res[p++] = num * (l_zero ? 1 : -1); //如果加的sum是正数，则0和sum会分别分配到l和r。如果sum是负整数，则sum会被split到l，而0会被split到r，因为是从左到右从小到大split的。
            //开始递归。
            sums = l_zero ? l : r; //哪边有0，表示需要递归哪边。画图一看就明白
        }
        return res;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{2, ArrayUtils.buildArray("[-605,9,-596,0]")}, {3, ArrayUtils.buildArray("[-3,-2,-1,0,0,1,2,3]")}, {2, ArrayUtils.buildArray("[0,0,0,0]")}, {4, ArrayUtils.buildArray("[0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]")},});
    }

    @Parameterized.Parameter
    public int n;
    @Parameterized.Parameter(1)
    public int[] sums;

    @Test
    public void test() {
        System.out.println(Arrays.toString(recoverArray(n, sums)));
    }

}
