package study.erik.algorithm.job.huawei.simulation06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/5 16:10
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public List<Integer> findSubstring(String s, String[] words) {
        String[] map = new String[s.length()];
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.merge(word, 1, Integer::sum);
            int idx = s.indexOf(word, 0);
            while (idx > -1) {
                map[idx] = word;
                idx = s.indexOf(word, idx + 1);
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            if (dsf(map, i, new HashMap<>(countMap))) {
                ret.add(i);
            }
        }
        return ret;
    }

    public boolean dsf(String[] map, int curIdx, Map<String, Integer> leftMap) {
        if (leftMap.isEmpty()) {
            return true;
        }
        if (curIdx >= map.length) {
            return false;
        }
        if (map[curIdx] == null) {
            return false;
        }
        String curWord = map[curIdx];
        Integer c = leftMap.getOrDefault(curWord, 0);
        if (c == 0) {
            return false;
        }
        c--;
        if (c == 0) {
            leftMap.remove(curWord);
        } else {
            leftMap.put(curWord, c);
        }
        return dsf(map, curIdx + curWord.length(), leftMap);
    }


    @Parameterized.Parameters
    public static Collection testData() {

        return Arrays.asList(new Object[][]{

                {ArrayUtils.buildList("[0]"), "ababababab", new String[]{"ababa", "babab"}},
                {ArrayUtils.buildList("[8]"), "wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}},
                {ArrayUtils.buildList("[]"), "wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}},
                {ArrayUtils.buildList("[0,9]"), "barfoothefoobarman", new String[]{"foo", "bar"}},
        });
    }

    @Parameterized.Parameter
    public List<Integer> expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public String[] words;

    @Test
    public void test() {
        Assert.assertEquals(expect, findSubstring(s, words));
    }

}
