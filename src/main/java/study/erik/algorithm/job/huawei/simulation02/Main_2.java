package study.erik.algorithm.job.huawei.simulation02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/2 11:23
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "",
            selfRemark = "最终没有全部通过，超时了。" +
                    "我们的努力：首先直接bfs，超时；然后双向bfs也超时——可见双向bfs的优化效果不好的。" +
                    "结论：关键是用掩码*来回避26个字符的切换。")
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        set1.add(beginWord);
        if (wordList.contains(endWord)) {
            set2.add(endWord);
        }
        int time = 1;
        while (true) {
            if (setHashCommon(set1, set2)) {
                return time;
            }
            set1 = next(set1, visited1, wordList);
            time++;
            if (set1.isEmpty()) {
                return 0;
            }
            if (setHashCommon(set1, set2)) {
                return time;
            }
            if (set2.isEmpty()) {
                return 0;
            }
            set2 = next(set2, visited2, wordList);
            time++;
        }
    }

    public boolean setHashCommon(Set<String> set1, Set<String> set2) {
        for (String s : set1) {
            if (set2.contains(s))
                return true;
        }
        return false;
    }

    public Set<String> next(Set<String> strList, Set<String> visited, List<String> wordList) {
        Set<String> nextStrSet = new HashSet<>();
        for (String s : strList) {
            nextStrSet.addAll(next(s, visited, wordList));
        }
        return nextStrSet;
    }

    public Set<String> next(String str, Set<String> visited, List<String> wordList) {
        Set<String> nextStrSet = new HashSet<>();
        int length = str.length();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            for (int j = 0; j < 26; j++) {
                char replaceChar = (char) (j + 'a');
                if (c == replaceChar) {
                    continue;
                }
                charArray[i] = replaceChar;
                String nextStr = String.valueOf(charArray);
                if (visited.contains(nextStr) || !wordList.contains(nextStr)) {
                    continue;
                }
                visited.add(nextStr);
                nextStrSet.add(nextStr);
            }
            charArray[i] = c;
        }
        return nextStrSet;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {5, "hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")},
                {0, "hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String beginWord;
    @Parameterized.Parameter(2)
    public String endWord;
    @Parameterized.Parameter(3)
    public List<String> wordList;

    @Test
    public void test() {
        Assert.assertEquals(expect, ladderLength(beginWord, endWord, wordList));
    }

}
