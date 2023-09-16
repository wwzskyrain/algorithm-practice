package study.erik.algorithm.leetcode.array.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/9/15 ，时间：15:48
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Substring_with_Concatenation_of_All_Words {

    @LetCodeCommit(title = "30. Substring with Concatenation of All Words",
            selfRemark = "这个题目倒是没有太多意思。不过也给了一点思考。" +
                    "首先这个题目不能正解：先求出words的所有全排列，在把每一个全排列到s中找子字符串。这两点都是很耗时的。" +
                    "如果正解不行，那就反方向来。" +
                    "把s中所有长度为words全排列长度的子字符串都拿出来一个一个来测试。")
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        //map中包含了words的全镜像。
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < s.length() - all_len + 1; i++) {
            String tmp = s.substring(i, i + all_len);
            //tmp是待测试的子字符串。
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j = 0; j < all_len; j += one_word) {
                String w = tmp.substring(j, j + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
            }
            if (map.equals(tmp_map)) res.add(i);
        }
        return res;
    }

}
