/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.gcd;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : NumberOfDifferentSubsequencesGCDs.java, v 0.1 2023年07月06日 06:51 yueyi Exp $
 */
public class NumberOfDifferentSubsequencesGCDs {

    @LetCodeCommit(title = "1819. Number of Different Subsequences GCDs")
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        boolean[] candidates = new boolean[max + 1];
        for (int num : nums) {
            candidates[num] = true;
        }
        int ans = 0;
        for (int i = 1; i < candidates.length; i++) {
            int subGcd = 0;
            for (int j = i; j <= max; j += i) {
                if (candidates[j]) {
                    //如果j在nums中
                    if (subGcd == 0) {
                        //初始化subGcd：第一次进来
                        subGcd = j;
                    } else {
                        //开始用i这个gcd去穿nums中的数据
                        subGcd = gcd(subGcd, j);
                    }
                    if (subGcd == i) {
                        //穿成功一次，说明i是某个sub_sequence的gcd，计数。
                        //穿成功的case: i=3， j={3}
                        //穿成功的case: i=3， j={6,9}
                        //穿失败的case: i=3， j={6,12}(此时gcd=6)
                        //穿成功的case: i=3， j={6,12,15}(此时gcd=3，在15加入的时候)
                        //注意以上case，j都是瞎编的，没个case的j都不是一样的。
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

}