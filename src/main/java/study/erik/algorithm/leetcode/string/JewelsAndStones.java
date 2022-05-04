/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : JewelsAndStones.java, v 0.1 2022年05月04日 17:46 yueyi Exp $
 */
public class JewelsAndStones {

    @LetCodeCommit(title = "771. Jewels and Stones")
    public int numJewelsInStones(String jewels, String stones) {

        int[] countMap = new int['z' - 'A' + 1];
        char[] chars = stones.toCharArray();
        for (char c : chars) {
            countMap[c - 'A']++;
        }
        int count = 0;
        char[] chars1 = jewels.toCharArray();
        for(char c: chars1){
            count += countMap[c - 'A'];
        }
        return count;


    }
}