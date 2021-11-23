/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : DecodeWays.java, v 0.1 2021年11月23日 8:29 上午 yueyi Exp $
 */
public class DecodeWays {

    @LetCodeCommit(title = "Decode Ways",
            selfRemark = "先看了历史提交记录，竟然错了那么多都没有正确，还是没有找到思路。"
                    + "重新搞，用dp，搞定，超时？加备忘录，搞定？分数低，再优化吧，todo")
    public int numDecodings(String s) {
        Set<String> dic = getDic();
        return decoding(s, dic, new HashMap<>());
    }

    public int decoding(String s, Set<String> dic, Map<String, Integer> map) {
        Integer num = map.get(s);
        if (num != null) {
            return num;
        }
        if (s.length() == 0) {
            return 0;
        }
        int c = 0;
        if (dic.contains(s)) {
            c++;
        }
        for (int i = 1; i < s.length(); i++) {
            String preSub = s.substring(0, i);
            if (dic.contains(preSub)) {
                String suffixSub = s.substring(i);
                c += decoding(suffixSub, dic, map);
            }
        }
        map.put(s, c);
        return c;
    }

    public Set<String> getDic() {
        Set<String> dic = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            dic.add(String.valueOf(i + 1));
        }
        return dic;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, numDecodings("12"));
        Assert.assertEquals(3, numDecodings("226"));
        Assert.assertEquals(0, numDecodings("0"));
        Assert.assertEquals(1836311903, numDecodings("111111111111111111111111111111111111111111111"));
    }

}