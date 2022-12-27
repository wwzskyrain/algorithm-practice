/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LetterTilePossibilities.java, v 0.1 2022年12月27日 21:36 yueyi Exp $
 */
public class LetterTilePossibilities {

    @LetCodeCommit(title = "1079. Letter Tile Possibilities")
    public int numTilePossibilities(String tiles) {
        int[] arr = new int[26];
        for (int i = 0; i < tiles.length(); i++) {
            arr[tiles.charAt(i) - 'A']++;
        }
        return backtrace(arr);
    }
    //这种回溯法，真的很奇妙。
    //解释一下：
    //1.length=1时，就是第一层backtrace，这是每个字母最多去一次；注意计数
    //2.length=2时, 就是第二层backtrace，是在第一层的基础上的所有的第二层，同里第一层，每个字母最多在第二层再出现一次；注意计数
    //3.length=3时，就是第三层backtrace，是在第二层的基础上的所有的第三层；同理，每个字母最多在第三层再出现一次；注意计数，
    private int backtrace(int[] arr) {
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) {
                continue;
            }
            sum++;
            arr[i]--;
            sum += backtrace(arr);
            arr[i]++;
        }
        return sum;
    }

}