package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 */
public class ReverseString {

    @LetCodeCommit(title = "344 Reverse String", selfRemark = "太简单了，就是打个卡，太晚了")
    public void reverseString(char[] s) {
        if (s.length == 0) {
            return;
        }
        int l = 0;
        int h = s.length - 1;
        while (l < h) {
            char tempChar = s[l];
            s[l] = s[h];
            s[h] = tempChar;
            l++;
            h--;
        }
    }


}
