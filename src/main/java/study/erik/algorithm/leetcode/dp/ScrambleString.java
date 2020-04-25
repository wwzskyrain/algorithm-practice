package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-04-20 12:44
 */
public class ScrambleString {

    /**
     * title = Scramble String
     * url = https://leetcode.com/problems/scramble-string/
     * 扩展：
     * 1.  medium = Validate IP Address
     * 2.  hard = Profitable Schemes
     * 3.  hard = Build Array Where You Can Find The Maximum Exactly K Comparisons
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        return dfsIsScramble(s1, s2, new HashMap<>(), new HashMap<>());
    }

    /**
     * 分析：首先分析题意，按照定义去做变幻操作，绝对是不行的；定义一看就是递归性质的，所以应该想到要从整体做递归思考。
     * 递归如何思考呢？如果s1和s2是s关系，
     * 则一定存在至少一种拆分，把s1和s2分别拆分成s1l和s1r、s2l和s2r，当然s1l和s2l是等长的，s1r和s2r是等长的。
     * 好了，超时，总是超时
     * 我用了两个'备忘录'还是超时了，算了，此路不通。其实一般是可以通过的。可能是这种方法提交的太多了，leetcode加大了粒度。
     * 转机来了：只要添加一个sort排序就可以了通过了；猜想可见dfs真的是硬伤呀，一个小小的sort并比较，就可以减少很多false的情况，
     * 大大减少了false的递归深度——即使止损，真的是很有用啊。但是我们不要靠这种小把戏呀。还是要求助于三维动态规划，而且我们
     * 已经看懂了三维动态规划了。
     *
     * <p>
     * 只能求助于三维动态规划了 https://blog.csdn.net/linhuanmars/article/details/24506703
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean dfsIsScramble(String s1, String s2, Map<String, Set<String>> scrambleMap, Map<String, Set<String>> unscrambleMap) {

        if (s1.equals(s2)) {
            return true;
        }
        Set<String> scrambleWords = scrambleMap.getOrDefault(s1, new HashSet<>());
        if (scrambleWords.contains(s2)) {
            return true;
        }
        Set<String> unscrambleWords = unscrambleMap.getOrDefault(s1, new HashSet<>());
        if (unscrambleWords.contains(s2)) {
            return false;
        }

        char[] s1CharArr = s1.toCharArray();
        Arrays.sort(s1CharArr);
        char[] s2CharArr = s2.toCharArray();
        Arrays.sort(s2CharArr);

        if (!String.valueOf(s1CharArr).equals(String.valueOf(s2CharArr))) {
            Set<String> unscrambleMapOrDefault = unscrambleMap.getOrDefault(s1, new HashSet<>());
            unscrambleMapOrDefault.add(s2);
            return false;
        }

        if (s1.length() == 1) {
            return false;
        }

        int index = 1;

        while (index < s1.length()) {
            String changedS1 = s1.substring(index) + s1.substring(0, index);
            if (changedS1.equals(s2)) {
                Set<String> set = scrambleMap.getOrDefault(s1, new HashSet<>());
                set.add(s2);
                return true;
            }
            String s1l = s1.substring(0, index);
            String s1r = s1.substring(index);
            if (dfsIsScramble(s1l, s2.substring(0, index), scrambleMap, unscrambleMap)
                    && dfsIsScramble(s1r, s2.substring(index), scrambleMap, unscrambleMap)) {
                Set<String> set = scrambleMap.getOrDefault(s1, new HashSet<>());
                set.add(s2);
                return true;
            }

            if (dfsIsScramble(s1r, s2.substring(0, s1r.length()), scrambleMap, unscrambleMap) &&
                    dfsIsScramble(s1l, s2.substring(s1r.length()), scrambleMap, unscrambleMap)) {
                Set<String> set = scrambleMap.getOrDefault(s1, new HashSet<>());
                set.add(s2);
                return true;
            }

            index++;
        }
        Set<String> unscrambleMapOrDefault = unscrambleMap.getOrDefault(s1, new HashSet<>());
        unscrambleMapOrDefault.add(s2);
        return false;
    }

    @Test
    public void test_() {

        String s9 = "abcdefghijklmnopq", s10 = "efghijklmnopqcadb";
        Assert.assertFalse(isScramble(s9, s10));

        String s7 = "lrgroncryswd", s8 = "orwcsdlnrgyr";
        Assert.assertFalse(isScramble(s7, s8));


        String s5 = "abc", s6 = "cba";
        Assert.assertTrue(isScramble(s5, s6));

        String s3 = "abcde", s4 = "caebd";
        Assert.assertFalse(isScramble(s3, s4));

        String s1 = "great", s2 = "rgeat";
        Assert.assertTrue(isScramble(s1, s2));
    }

}
