/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yueyi
 * @version : SmallestSufficientTeam.java, v 0.1 2022年12月30日 22:45 yueyi Exp $
 */
public class SmallestSufficientTeam {

    @LetCodeCommit(title = "1125. Smallest Sufficient Team")
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int ns = req_skills.length, np = people.size();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < ns; ++i) map.put(req_skills[i], i);
        List<Integer>[] suff = new List[1 << ns];
        suff[0] = new ArrayList<>();
        for (int i = 0; i < np; ++i) {
            int skill = 0;
            for (String s : people.get(i)) skill |= (1 << map.get(s));
            for (int prev = 0; prev < suff.length; ++prev) {
                if (suff[prev] == null) continue;
                int comb = prev | skill;
                if (suff[comb] == null || suff[prev].size() + 1 < suff[comb].size()) {
                    suff[comb] = new ArrayList<>(suff[prev]);
                    suff[comb].add(i);
                }
            }
        }
        List<Integer> res = suff[(1 << ns) - 1];
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

}