/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : MapSumPairs.java, v 0.1 2022年04月17日 6:00 下午 yueyi Exp $
 */
@LetCodeCommit(title = "677. Map Sum Pairs",
        diff = "m",
        selfRemark = "前缀计数问题，很简单的.成绩还好， 85%和75%")
public class MapSumPairs {

    public static class MapSum {

        Map<String, Integer> map = new HashMap<>();

        public MapSum() {
        }

        public void insert(String key, int val) {
            String keyId = key + "_";
            Integer oldValue = map.getOrDefault(keyId, 0);
            for (int i = 1; i <= key.length(); i++) {
                String preKey = key.substring(0, i);
                map.put(preKey, map.getOrDefault(preKey, 0) - oldValue + val);
            }
            map.put(keyId, val);
        }

        public int sum(String prefix) {
            return map.getOrDefault(prefix, 0);
        }
    }

    @Test
    public void test_1() {
        //        ["MapSum","insert","sum","insert","sum","insert","sum"]
        //[[],["apple",3],["ap"],["app",2],["ap"],["apple",5],["ap"]]

        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        Assert.assertEquals(3, mapSum.sum("ap"));
        mapSum.insert("app", 2);
        Assert.assertEquals(5, mapSum.sum("ap"));
        mapSum.insert("apple", 5);
        Assert.assertEquals(7, mapSum.sum("ap"));
    }

}