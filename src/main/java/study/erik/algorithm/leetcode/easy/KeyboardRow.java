/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : KeyboardRow.java, v 0.1 2022年03月02日 9:37 上午 yueyi Exp $
 */
public class KeyboardRow {

    @LetCodeCommit(title = "500. Keyboard Row")
    public String[] findWords(String[] words) {

        int[] map = new int[26];
        String first = "qwertyuiop";
        for (int i = 0; i < first.length(); i++) {
            map[first.charAt(i) - 'a'] = 1;
        }
        String second = "asdfghjkl";
        for (int i = 0; i < second.length(); i++) {
            map[second.charAt(i) - 'a'] = 2;
        }
        String third = "zxcvbnm";
        for (int i = 0; i < third.length(); i++) {
            map[third.charAt(i) - 'a'] = 3;
        }

        List<String> ret = new ArrayList<>();
        for (String word : words) {
            int line = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c < 'a') {
                    c += ('a' - 'A');
                }
                int l = map[c - 'a'];
                if (line == 0) {
                    line = l;
                } else {
                    if (line != l) {
                        line = -1;
                        break;
                    }
                }
            }
            if (line > -1) {
                ret.add(word);
            }
        }
        return ret.toArray(new String[0]);
    }

}