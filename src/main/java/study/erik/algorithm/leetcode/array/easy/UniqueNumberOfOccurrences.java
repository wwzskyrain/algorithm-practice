/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : UniqueNumberOfOccurrences.java, v 0.1 2023年03月12日 08:34 yueyi Exp $
 */
public class UniqueNumberOfOccurrences {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> m = new HashMap();
        for(int i : arr){
            int c = m.getOrDefault(i, 0);
            c++;
            m.put(i, c);
        }
        Set<Integer> s = new HashSet(m.values());
        return m.keySet().size() == s.size();
    }

}