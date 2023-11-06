package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/6 21:42
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Product_of_Word_Lengths {

    @LetCodeCommit(title = "318. Maximum Product of Word Lengths",
    selfRemark = "medium，虽然是一个m题，我能用最大最小来分析，而且没怎么调试就过了，成就感不小。可是排名不高呀？" +
            "明白了，我这个本质上没有快多少，因为我有一个排序，相对n^2的解法，真的没有先进很多." +
            "不过，如果遇到已经排好序的数据呢，咱这个方法就是好方法啦。")
    public int maxProduct(String[] words) {
        int max = 0;
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<Integer> list = preResolve(words);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int last_1 = j;
            int last_2 = j - 1;
            while (last_2 >= i) {
                if ((list.get(last_1) & list.get(last_2)) == 0) {
                    int l1 = words[last_1].length();
                    int l2 = words[last_2].length();
                    max = Math.max(max, l1 * l2);
                    break;
                }
                last_2--;
            }
            if (last_2 < i) {
                j = last_1 - 1;
            } else {
                //i要往前走了
                i = last_2;
                j = last_1 - 1;
            }
        }
        return max;
    }

    public List<Integer> preResolve(String[] nums) {
        List<Integer> list = new ArrayList<>();
        for (String word : nums) {
            int n = 0;
            int l = word.length();
            for (int i = 0; i < l; i++) {
                n = n | (1 << (word.charAt(i) - 'a'));
            }
            list.add(n);
        }
        return list;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {16, new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}},
                {4, new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}},
                {0, new String[]{"a", "aa", "aaa", "aaaa"}},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String[] words;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxProduct(words));
    }

}
