package study.erik.algorithm.leetcode.string.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/4/1 08:05
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class FaultyKeyboard {

    @LetCodeCommit(title = "2810. Faulty Keyboard")
    public String finalString(String s) {
        int l = s.length();
        char[] arr = s.toCharArray();
        Deque<Character> q = new ArrayDeque<>();
        boolean head = false;
        for (int i = 0; i < l; i++) {
            char c = arr[i];
            if (c == 'i') {
                head = !head;
            } else {
                if (head) {
                    q.addFirst(c);
                } else {
                    q.addLast(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (head) {
            while (!q.isEmpty()) {
                sb.append(q.pollLast());
            }
        } else {
            while (!q.isEmpty()) {
                sb.append(q.pollFirst());
            }
        }
        return sb.toString();
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {"f", "fii"},
                {"rtsng", "string"},
                {"ponter", "poiinter"}
        });
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String s;

    @Test
    public void test() {
        Assert.assertEquals(expect, finalString(s));
    }

}
