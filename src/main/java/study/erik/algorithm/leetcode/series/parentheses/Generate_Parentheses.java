package study.erik.algorithm.leetcode.series.parentheses;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/28 21:58
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Generate_Parentheses {

    @LetCodeCommit(title = "22. Generate Parentheses", selfRemark = "还有dfs的解法，也不错")
    public List<String> generateParenthesis(int n) {
        List<String>[] lists = new List[n + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        lists[0].add("");
        lists[1].add("()");
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int k = i - j - 1;
                List<String> listJ = lists[j];
                List<String> listK = lists[k];
                for (String sJ : listJ) {
                    for (String sK : listK) {
                        lists[i].add(String.format("(%s)%s", sJ, sK));
                    }
                }
            }
        }
        return lists[n];
    }


}
